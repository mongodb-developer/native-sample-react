package io.ionic.starter

import android.util.Log
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import io.realm.kotlin.where
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.types.ObjectId
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask


open class QsTask() : RealmObject() {
    @PrimaryKey
    var _id: ObjectId = ObjectId(Date())

    var name: String = "task"

    @Required
    var status: String = ""

    var _partition: String = ""
    var owner: String? = ""
}


@CapacitorPlugin(name = "Echo")
class EchoPlugin : Plugin() {
//    var backgroundThreadRealm : Realm? = null
    lateinit var uiThreadRealm: Realm

    @PluginMethod
    fun login(call: PluginCall) {
        Log.d("login", "Login called")

        val credentials: Credentials = Credentials.anonymous()
        app?.loginAsync(credentials) {
            if (it.isSuccess) {
                Log.v("IONIC_QUICKSTART", "Successfully authenticated anonymously.")
                val user: User? = app!!.currentUser()
                val partitionValue: String = "123"
                val config = SyncConfiguration.Builder(user, partitionValue)
                    .build()
                this.uiThreadRealm = Realm.getInstance(config)
                addChangeListenerToRealm(uiThreadRealm)
//                val task : FutureTask<String> =
//                    FutureTask(BackgroundQuickStart(app!!.currentUser()!!, call), "test")
//                val executorService: ExecutorService = Executors.newFixedThreadPool(2)
//                executorService.execute(task)
            } else {
                Log.e("IONIC_QUICKSTART", "Failed to log in. Error: ${it.error}")
            }

            val value = call.getString("value")
            val ret = JSObject()
            ret.put("value", value)
            call.resolve(ret)
        }
    }


    @PluginMethod
    fun echo(call: PluginCall) {
        Log.d("INSERT", "Insert task started")

        val task = QsTask()
        task.name = "Android New Task " + Date().toString()
        uiThreadRealm.executeTransaction { transactionRealm ->
            transactionRealm.insert(task)

            // return value back to Ionic JS
            val value = task.name
            val ret = JSObject()
            ret.put("value", value)
            call.resolve(ret)
        }
    }

    fun addChangeListenerToRealm(realm : Realm) {
        // all tasks in the realm
        val tasks : RealmResults<QsTask> = realm.where<QsTask>().findAllAsync()
        tasks.addChangeListener(OrderedRealmCollectionChangeListener<RealmResults<QsTask>> { collection, changeSet ->
            // process deletions in reverse order if maintaining parallel data structures so indices don't change as you iterate
            val deletions = changeSet.deletionRanges
            for (i in deletions.indices.reversed()) {
                val range = deletions[i]
                Log.v("QUICKSTART", "Deleted range: ${range.startIndex} to ${range.startIndex + range.length - 1}")
            }
            val insertions = changeSet.insertionRanges
            for (range in insertions) {
                Log.v("QUICKSTART", "Inserted range: ${range.startIndex} to ${range.startIndex + range.length - 1}")
            }
            val modifications = changeSet.changeRanges
            for (range in modifications) {
                Log.v("QUICKSTART", "Updated range: ${range.startIndex} to ${range.startIndex + range.length - 1}")
            }
        })
    }
}