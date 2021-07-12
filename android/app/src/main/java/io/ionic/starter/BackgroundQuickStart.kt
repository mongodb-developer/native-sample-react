package io.ionic.starter

import com.getcapacitor.JSObject
import com.getcapacitor.PluginCall
import io.realm.RealmResults
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration

class BackgroundQuickStart(val user: User, val call: PluginCall) : Runnable {
    override fun run() {
//        val partitionValue: String = "My Project"
//        val config = SyncConfiguration.Builder(user, partitionValue)
//            .build()
//        val backgroundThreadRealm : Realm = Realm.getInstance(config)
//        val task : Task = Task("New Task", partitionValue)
//        backgroundThreadRealm.executeTransaction { transactionRealm ->
//            transactionRealm.insert(task)
//        }
//        // all tasks in the realm
//        val tasks : RealmResults<Task> = backgroundThreadRealm.where<Task>().findAll()
//        // you can also filter a collection
//        val tasksThatBeginWithN : List<Task> = tasks.where().beginsWith("name", "N").findAll()
//        val openTasks : List<Task> = tasks.where().equalTo("status", TaskStatus.Open.name).findAll()
//        val otherTask: Task = tasks[0]!!
//        // all modifications to a realm must happen inside of a write block
//        backgroundThreadRealm.executeTransaction { transactionRealm ->
//            val innerOtherTask : Task = transactionRealm.where<Task>().equalTo("_id", otherTask._id).findFirst()!!
//            innerOtherTask.status = TaskStatus.Complete.name
//        }
//        val yetAnotherTask: Task = tasks.get(0)!!
//        val yetAnotherTaskId: ObjectId = yetAnotherTask._id
//        // all modifications to a realm must happen inside of a write block
//        backgroundThreadRealm.executeTransaction { transactionRealm ->
//            val innerYetAnotherTask : Task = transactionRealm.where<Task>().equalTo("_id", yetAnotherTaskId).findFirst()!!
//            innerYetAnotherTask.deleteFromRealm()
//        }
//        // because this background thread uses synchronous realm transactions, at this point all
//        // transactions have completed and we can safely close the realm
//        backgroundThreadRealm.close()

        // return value back to Ionic JS
        val value = call.getString("value")
        val ret = JSObject()
        ret.put("value", value)
        call.resolve(ret)
    }
}