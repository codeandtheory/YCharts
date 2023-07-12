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
    @State private var isPieChartPresented = false
    @State private var isDonutChartPresented = false
    @State private var isBubbleChartPresented = false
    @State private var isCombinedChartPresented = false
    
    
    
    
    
    var body: some View {
        NavigationStack {
            VStack() {
                Button(action: {
                    self.isBarChartPresented.toggle()
                }) {
                    Text("Bar Chart")
                        .padding()
                        .font(.body)
                        .frame(maxWidth: .infinity)
                        .foregroundColor(.white)
                        .background(Color.black)
                }.navigationDestination(isPresented: $isBarChartPresented) {
                    MainView(chartType: 1)
                        .navigationTitle("Bar Chart")
                }.frame(maxWidth: .infinity).padding()
                
                Button(action: {
                    self.isLineChartPresented.toggle()
                }) {
                    Text("Line Chart")
                        .padding()
                        .font(.body)
                        .frame(maxWidth: .infinity)
                        .foregroundColor(.white)
                        .background(Color.black)
                }.navigationDestination(isPresented: $isLineChartPresented) {
                    MainView(chartType: 3)
                        .navigationTitle("Line Chart")
                }.frame(maxWidth: .infinity).padding()
                
                Button(action: {
                    self.isWaveChartPresented = true
                }) {
                    Text("Wave Chart")
                        .padding()
                        .font(.body)
                        .frame(maxWidth: .infinity)
                        .foregroundColor(.white)
                        .background(Color.black)
                    
                }.navigationDestination(isPresented: $isWaveChartPresented) {
                    MainView(chartType: 2)
                        .navigationTitle("Wave Chart")
                }.frame(maxWidth: .infinity).padding()
                
                Button(action: {
                    self.isPieChartPresented = true
                }) {
                    Text("Pie Chart")
                        .padding()
                        .font(.body)
                        .frame(maxWidth: .infinity)
                        .foregroundColor(.white)
                        .background(Color.black)
                    
                }.navigationDestination(isPresented: $isPieChartPresented) {
                    MainView(chartType: 4)
                        .navigationTitle("Pie Chart")
                }.frame(maxWidth: .infinity).padding()
                
                Button(action: {
                    self.isDonutChartPresented = true
                }) {
                    Text("Donut Pie Chart")
                        .padding()
                        .frame(maxWidth: .infinity)
                        .font(.body)
                        .foregroundColor(.white)
                        .background(Color.black)
                    
                }.navigationDestination(isPresented: $isDonutChartPresented) {
                    MainView(chartType: 5)
                        .navigationTitle("Donut Pie Chart")
                }.frame(maxWidth: .infinity).padding()
                
                
                Button(action: {
                    self.isBubbleChartPresented = true
                }) {
                    Text("Bubble Chart")
                        .padding()
                        .frame(maxWidth: .infinity)
                        .font(.body)
                        .foregroundColor(.white)
                        .background(Color.black)

                }.navigationDestination(isPresented: $isBubbleChartPresented) {
                    MainView(chartType: 6)
                        .navigationTitle("Bubble Chart")
                }.frame(maxWidth: .infinity).padding()
                
                
                    Button(action: {
                    self.isCombinedChartPresented = true
                }) {
                    Text("Combined Chart")
                        .padding()
                        .frame(maxWidth: .infinity)
                        .font(.body)
                        .foregroundColor(.white)
                        .background(Color.black)
                    
                }.navigationDestination(isPresented: $isCombinedChartPresented) {
                    MainView(chartType: 7)
                        .navigationTitle("Combined Chart")
                }.frame(maxWidth: .infinity).padding()
                
                
                
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
