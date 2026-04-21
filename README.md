<div align="center">

# 🚌 CoU Bus Tracker

### Real-Time Bus Tracking App for Comilla University

[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android&logoColor=white)](https://android.com)
[![Language](https://img.shields.io/badge/Language-Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://java.com)
[![Firebase](https://img.shields.io/badge/Firebase-Realtime_DB-FFCA28?style=flat-square&logo=firebase&logoColor=black)](https://firebase.google.com)
[![Maps](https://img.shields.io/badge/Google_Maps_API-4285F4?style=flat-square&logo=google-maps&logoColor=white)](https://developers.google.com/maps)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)](LICENSE)

> A real-time Android application built to help **Comilla University** students track campus bus locations, view live schedules, and never miss their bus again.

</div>

---

## 📱 About The App

**CoU Bus Tracker** solves a common problem faced by Comilla University students — uncertainty about bus timings and locations. Instead of waiting blindly at the stop, students can now track buses in real-time directly from their Android phone.

---

## ✨ Features

- 🗺️ **Live Bus Location** — Track buses on an interactive Google Map in real-time
- 🔥 **Real-Time Sync** — Firebase Realtime Database ensures instant location updates
- 📅 **Bus Schedules** — View up-to-date bus timetables for all routes
- 📍 **Dynamic Updates** — Location and schedule data updates automatically without refresh
- 🎨 **User-Friendly UI** — Clean and intuitive interface for quick access to bus info
- ⚡ **Low Latency** — Optimized for real-time responsiveness on mobile networks

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| **Java** | Core Android development language |
| **Android Studio** | IDE for building and testing the app |
| **Firebase Realtime Database** | Live bus location data sync |
| **Firebase Authentication** | User authentication (if applicable) |
| **Google Maps API** | Interactive map and location display |
| **Gradle** | Build automation |

---

## 🚀 Getting Started

### Prerequisites

- Android Studio (Arctic Fox or later)
- Android device / emulator (API 21+)
- Google account for Firebase & Maps API

### Installation

**1. Clone the repository**
```bash
git clone https://github.com/mdtareqhasan/CoUBusTracker.git
cd CoUBusTracker
```

**2. Open in Android Studio**
```
File → Open → Select the CoUBusTracker folder
```

**3. Setup Firebase**
- Go to [Firebase Console](https://console.firebase.google.com)
- Create a new project → Add Android app
- Download `google-services.json`
- Place it inside `app/` folder

**4. Setup Google Maps API**
- Go to [Google Cloud Console](https://console.cloud.google.com)
- Enable **Maps SDK for Android**
- Create an API key
- Add it to `AndroidManifest.xml`:
```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY_HERE" />
```

**5. Run the app**
- Connect your Android device or start an emulator
- Click **Run ▶** in Android Studio

---

## 📁 Project Structure

```
CoUBusTracker/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/          # Java source files
│   │   │   ├── res/           # Layouts, drawables, strings
│   │   │   └── AndroidManifest.xml
│   └── build.gradle
├── gradle/
├── build.gradle
└── settings.gradle
```

---

## 🔧 Firebase Database Structure

```json
{
  "buses": {
    "bus_01": {
      "latitude": 23.4607,
      "longitude": 91.1809,
      "route": "Campus → City",
      "last_updated": "timestamp"
    }
  },
  "schedules": {
    "morning": "7:30 AM",
    "afternoon": "1:00 PM",
    "evening": "5:00 PM"
  }
}
```

---

## 📸 Screenshots

> *(Add screenshots of your app here)*
> 
> You can drag & drop images into this section on GitHub.

---

## 🎯 What I Learned

Building this project helped me strengthen my skills in:
- **Real-time data handling** with Firebase Realtime Database
- **Android mobile architecture** and activity/fragment lifecycle
- **Google Maps SDK** integration and location services
- **User-centered design** for a mobile audience
- **Asynchronous programming** for smooth UI experience

---

## 🔮 Future Improvements

- [ ] Push notifications when bus is nearby
- [ ] Driver-side app for live location sharing
- [ ] Offline schedule access
- [ ] ETA (Estimated Time of Arrival) calculation
- [ ] Dark mode support
- [ ] Multi-language support (Bengali & English)

---

## 👨‍💻 Developer

**Md. Tareq Hasan**
- 🎓 CSE Student @ Comilla University (2023–2027)
- 💼 [LinkedIn](https://www.linkedin.com/in/md-tareq-hasan-801801343/)
- 🐙 [GitHub](https://github.com/mdtareqhasan)

---

## 📄 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

<div align="center">

⭐ **If this project helped you, please give it a star!** ⭐

</div>
