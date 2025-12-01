# 🍪 Oreo Clicker — Android Game

A smooth, animated, Cookie-Clicker–style idle game built in Java for Android.
Tap Oreos, buy upgrades, collect golden cookies, and watch your OPS (Oreos Per Second) soar.

📱 Features
⭐ Core Gameplay

Tap the Oreo to earn cookies

Incremental upgrades through a full-featured Shop Activity

Golden Oreos spawn at random intervals with animation effects

Clicking one gives +1000 Oreos

Idle generation: Earn Oreos per second based on upgrades (OPS)

🌟 UI & Animation

Screenshots:
<img width="300" height="2400" alt="image" src="https://github.com/user-attachments/assets/a90746fb-8bdb-4fa4-936c-7c14a0be9e0d" />
<img width="300" height="1442" alt="image" src="https://github.com/user-attachments/assets/3e844cf7-9414-41e8-9dd6-3a546135b298" />
<img width="300" height="2400" alt="image" src="https://github.com/user-attachments/assets/52600df9-af6e-47f5-912b-9b21552de5e4" />


Smooth scaling animations on cookie tap

Floating “+X” text animations

Rotating shine graphic behind the cookie

Animated shop and settings icons

Randomized spawning position + animation set for golden Oreos

⚙️ Persistent Progress

Fully stored via SharedPreferences:

Oreos owned

Name

Upgrades purchased

OPS (Oreos Per Second)

🛒 Shop System

Upgradeable Oreo types:

Oreo Minis

Mint Oreo

Double Stuf

Golden Oreo

Birthday Cake Oreo

MEGA Stuf

Each upgrade has:

Cost

OPS contribution

Image

Current owned count

Data is passed between Activities using Parcelable.

🧰 Settings Menu

Accessible via popup:

Reset all progress

(Space for volume slider functionality)

🧱 Project Structure
/app/src/main/java/com/example/oreoclicker/
│
├── MainActivity.java         # Core game logic & visuals
├── Shop.java                 # Upgrade purchasing activity
├── Oreos.java                # Parcelable data model for upgrades
│
/app/src/main/res/
│   ├── layout/               # XML UI layouts
│   ├── drawable/             # Oreo images, icons
│   ├── anim/                 # Rotation, scale, fade animations
│   ├── raw/                  # Click sound effects

🔧 Technical Highlights

AtomicInteger for thread-safe cookie counting

AsyncTask to avoid blocking UI on click increments

ActivityResultLauncher to receive shop updates

ConstraintLayout + ConstraintSet for dynamic placement of animated views

ObjectAnimator + AnimatorSet for compound animation sequences

Handler running recurring timed updates (golden Oreos + OPS ticker)

▶️ How to Run

Clone the repository:

git clone https://github.com/yourname/oreo-clicker.git


Open in Android Studio

Sync Gradle

Run on emulator or physical device (Android 8.0+ recommended)
