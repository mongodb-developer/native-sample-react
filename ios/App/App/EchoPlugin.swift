//
//  EchoPlugin.swift
//  App
//
//  Created by Michael Hartington on 5/4/21.
//

import Capacitor

@objc(EchoPlugin)
public class EchoPlugin: CAPPlugin {
    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        print("⚡️⚡️ Value in native switf", value)
        call.resolve(["value": value])
    }
}
