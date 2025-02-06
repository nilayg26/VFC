package com.example.vfc.ViewModel
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vfc.createToastMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
class UserViewModel :ViewModel(){
    private  var auth=FirebaseAuth.getInstance()
    private  val _authState = MutableLiveData<State>()
    val authState:LiveData<State> = _authState
    private var db=FirebaseFirestore.getInstance()
    @SuppressLint("SuspiciousIndentation")
    fun logIn(context: Context, sharedPreferences: SharedPreferences, pass:String){
        _authState.value=Loading
        val email=sharedPreferences.getString("email","")
            if (email != null) {
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    task->
                    if (task.isSuccessful){
                        if (auth.currentUser?.isEmailVerified==true) {
                            sharedPreferences.edit().putBoolean("LoginStatus", true).apply()
                            val uid = auth.uid
                            getUser(uid.toString(), sharedPreferences, context)
                            _authState.value = Authenticated
                        }
                        else{
                            context.createToastMessage("Check your email to verify")
                            _authState.value=EmailNotVerified
                        }
                    }
                    else {
                        context.createToastMessage(task.exception?.message.toString())
                            _authState.value = Error(task.exception?.message ?: "Something went wrong")
                        }
                }
            }
    }
    fun signUp(context: Context,sharedPreferences: SharedPreferences,pass:String){
        _authState.value=Loading
        val email=sharedPreferences.getString("email","")
        val name=sharedPreferences.getString("name","")
        val reg=sharedPreferences.getString("reg","")
        if (email != null && name!=null && reg!=null) {
            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                task->
                if (task.isSuccessful) {
                    auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                        context.createToastMessage("Check your inbox to verify and login")
                        sharedPreferences.edit().putString("uid",auth.uid).apply()
                        //send data to firebase
                       val boolean= addUser(email, name, reg,context)
                        if (boolean){
                        _authState.value = EmailNotVerified
                        }
                        else{
                            context.createToastMessage("Something went wrong")
                            _authState.value=Error("Something went wrong")
                        }
                    }
                }
                else{
                    context.createToastMessage(task.exception?.message.toString())
                    _authState.value = Error(task.exception?.message ?: "Something went wrong")
                }
            }
        }

    }
    fun signOut(context: Context,sharedPreferences: SharedPreferences){
        sharedPreferences.edit().clear().apply()
        _authState.value=Unauthenticated
        auth.signOut()
    }
    private fun addUser(email: String, name: String, reg: String, context: Context) :Boolean {
        val user = User(email = email, name = name, reg = reg)
        try {
            auth.uid?.let {
                db.collection("Users").document(it).set(user)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            println("User Added");
                        } else {
                            context.createToastMessage(task.exception?.message.toString())
                            println("error from add user: " + task.exception?.message)
                        }
                    }
            }
            return true
        } catch (e: Exception) {
            context.createToastMessage(e.message.toString())
            return false
        }
    }
    private fun getUser(uid: String, sharedPreferences: SharedPreferences, context: Context){
        db.collection("Users").document(uid).get().addOnFailureListener { e ->
            Log.d("Firestore", "Error fetching documents", e)
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.result.exists()) {
                    val snapshot = task.result
                    val name = snapshot.data?.get("name").toString()
                    context.createToastMessage("Welcome $name")
                    val reg = snapshot.data?.get("reg").toString()
                    val email = snapshot.data?.get("email").toString()
                    sharedPreferences.edit().putString("name",name).
                            putString("reg",reg).
                            putString("email",email)
                        .apply()
                } else {
                    println("From getUser(): User does not exists")
                }
            } else {
                context.createToastMessage(task.exception?.message.toString())
                println("From getUser(): ${task.exception?.message.toString()}")
                _authState.value = Error(task.exception?.message.toString())
            }
        }
    }
    fun forgotPassword(email: String,context: Context){
        _authState.value=Loading
        auth.sendPasswordResetEmail(email).addOnCompleteListener{
            task->
            if (task.isSuccessful){
                try {
                    context.createToastMessage("Check inbox")
                    _authState.value = EmailNotVerified
                }
                catch (e:Exception){
                    val err="Account not found"
                    context.createToastMessage(err)
                    println("From forgotPassword(): ${e.message}")
                    _authState.value=Error(err)
                }
            }
            else{
                context.createToastMessage(task.exception?.message.toString())
                println("From forgotPassword(): ${task.exception?.message.toString()}")
                _authState.value=Error(task.exception?.message.toString())
            }
        }
    }
}
interface State{
    var value:String
}
object Loading:State{
    override var value="loading"
}
object EmailNotVerified:State{
    override var value="EmailNotVerified"
}
object Authenticated:State{
    override var value="authen"
}
object Unauthenticated:State{
    override var value="unauthen"
}
data class Error(val msg:String):State{
    override var value="Error"
}
data class User(
    val name:String,
    val reg:String,
    val email:String,
)