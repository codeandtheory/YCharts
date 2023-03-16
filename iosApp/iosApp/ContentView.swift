//
//  ContentView.swift
//  iosApp
//
//  Created by Admin on 03/03/23.
//

import SwiftUI
import Foundation
import KMMYCharts

struct ContentView: View {
    @State private var isBarChartPresented = false
    @State private var isWaveChartPresented = false
    @State private var isLineChartPresented = false


    var body: some View {
        NavigationStack {
            VStack(spacing: 20) {
                Button(action: {
                    self.isBarChartPresented.toggle()
                }) {
                    Text("Bar Chart")
                        .padding()
                        .font(.body)
                        .foregroundColor(.white)
                        .background(Color.black)
                }.navigationDestination(isPresented: $isBarChartPresented) {
                    MainView(chartType: 1)
                        .navigationTitle("Bar Chart")
                }.frame(maxWidth: .infinity)
                
//                Button(action: {
//                    self.isLineChartPresented.toggle()
//                }) {
//                    Text("Line Chart")
//                        .padding()
//                        .font(.body)
//                        .foregroundColor(.white)
//                        .background(Color.black)
//                }.navigationDestination(isPresented: $isLineChartPresented) {
//                    MainView(chartType: 2)
//                }.navigationTitle("Line Chart")
                
                Button(action: {
                    self.isWaveChartPresented = true
                }) {
                    Text("Wave Chart")
                        .padding()
                        .font(.body)
                        .foregroundColor(.white)
                        .background(Color.black)
                        
                }.navigationDestination(isPresented: $isWaveChartPresented) {
                    MainView(chartType: 2)
                        .navigationTitle("Wave Chart")
                }.frame(maxWidth: .infinity)
            }
        }
    }
}

struct MainView: UIViewControllerRepresentable {
    typealias UIViewControllerType = UIViewController
    @State var chartType: Int

    func makeUIViewController(context: Context) -> UIViewController {
        let vc = Main_iosKt.MainViewController(chartType: Int32(chartType))
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
