//
//  TalkbackStateObserver.swift
//  iosApp
//
//  Created by Admin on 21/04/23.
//

import AVFoundation

class TalkBackStateObserver: ObservableObject {
    @Published var isTalkBackEnabled: Bool = false
    
    init() {
        let notificationCenter = NotificationCenter.default
        notificationCenter.addObserver(self, selector: #selector(handleInterruption(_:)), name: AVAudioSession.interruptionNotification, object: nil)
    }
    
    deinit {
        let notificationCenter = NotificationCenter.default
        notificationCenter.removeObserver(self)
    }
    
    @objc private func handleInterruption(_ notification: Notification) {
        guard let userInfo = notification.userInfo,
              let interruptionTypeValue = userInfo[AVAudioSessionInterruptionTypeKey] as? UInt,
              let interruptionType = AVAudioSession.InterruptionType(rawValue: interruptionTypeValue) else {
            return
        }
        
        switch interruptionType {
        case .began:
            isTalkBackEnabled = true
        case .ended:
            // check if TalkBack is still playing
            let session = AVAudioSession.sharedInstance()
            isTalkBackEnabled = session.isOtherAudioPlaying && session.category == .playback
        @unknown default:
            break
        }
    }
}
