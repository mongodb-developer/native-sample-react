//
//  EchoPlugin.swift
//  App
//
//  Created by Michael Hartington on 5/4/21.
//

import Capacitor
import RealmSwift

// MARK: - Model class for our Realm

class QsTask: Object {
    @objc dynamic var _id: ObjectId = ObjectId.generate()
    @objc dynamic var name: String = ""
    @objc dynamic var _partition: String = ""
    @objc dynamic var owner: String?
    @objc dynamic var status: String = ""
    
    override static func primaryKey() -> String? {
        return "_id"
    }

    convenience init(name: String, _partition: String) {
        self.init()
        self.name = name
        self._partition = _partition
    }
}

// MARK: - Plugin

@objc(EchoPlugin)
public class EchoPlugin: CAPPlugin {

    let app = App(id: "ionictest-uicsb") // Replace YOUR_REALM_APP_ID with your Realm app ID

    @objc func login(_ call: CAPPluginCall) {
        
        print("Login started")

        // Log in anonymously.
        app.login(credentials: Credentials.anonymous) { (result) in
            // Remember to dispatch back to the main thread in completion handlers
            // if you want to do anything on the UI.
            DispatchQueue.main.async {
                let value = call.getString("value") ?? ""

                switch result {
                case .failure(let error):
                    print("Login failed: \(error)", value)
                case .success(let user):
                    print("Login as \(user) succeeded!", value)
                    // Continue below
                }
                
                call.resolve(["value": value])
            }
        }
    }
    
    @objc func echo(_ call: CAPPluginCall) {
        
        onLogin()
        
        func onLogin() {
            // Now logged in, do something with user
            let user = app.currentUser!

            // The partition determines which subset of data to access.
            let partitionValue = "123"

            // Get a sync configuration from the user object.
            var configuration = user.configuration(partitionValue: partitionValue)
            // Open the realm asynchronously to ensure backend data is downloaded first.
            Realm.asyncOpen(configuration: configuration) { (result) in
                switch result {
                case .failure(let error):
                    print("Failed to open realm: \(error.localizedDescription)")
                    // Handle error...
                case .success(let realm):
                    // Realm opened
                    onRealmOpened(realm)
                }
            }
        }
        
        func onRealmOpened(_ realm: Realm) {
            // Get all tasks in the realm
            let tasks = realm.objects(QsTask.self)
            // Retain notificationToken as long as you want to observe
            let notificationToken = tasks.observe { (changes) in
                switch changes {
                case .initial: break
                    // Results are now populated and can be accessed without blocking the UI
                case .update(_, let deletions, let insertions, let modifications):
                    // Query results have changed.
                    print("Deleted indices: ", deletions)
                    print("Inserted indices: ", insertions)
                    print("Modified modifications: ", modifications)
                case .error(let error):
                    // An error occurred while opening the Realm file on the background worker thread
                    fatalError("\(error)")
                }
            }
            
            // Add some tasks
            let task = QsTask(name: "⚡️⚡️ Value in native switf \(Date())", _partition: "123")
             try! realm.write {
                 realm.add(task)
             }
            
            
        }
        
        
        let value = call.getString("value") ?? ""
        print("⚡️⚡️ Value in native switf", value)
        call.resolve(["value": value])
    }
}
