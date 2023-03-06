//
//  ContentView.swift
//  iosApp
//
//  Created by Admin on 03/03/23.
//

import SwiftUI
import KMMYCharts


struct ContentView: View {
    var body: some View {
        MainView()
    }
}

struct MainView: UIViewControllerRepresentable {
    typealias UIViewControllerType = UIViewController

    func makeUIViewController(context: Context) -> UIViewController {
        let vc = Main_iosKt.MainViewController()
        return vc
    }

        func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
            // Updates the state of the specified view controller with new information from SwiftUI.
        }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
