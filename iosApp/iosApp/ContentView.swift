import NotesApp
import SwiftUI
import shared
import UIKit

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
	var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard)
	}
}
