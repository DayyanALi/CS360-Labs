# 26100007-EmotiLog — Class Documentation

## Overview
EmotiLog is an Android app that lets users log emotions by pressing emoticon buttons. It stores each entry with a timestamp in a local Room database and provides a log view and daily summary screen.

---

## Data Layer

### EmoticonEntry
- **Type:** Room Entity (`@Entity`)
- **Table:** `emotion_log`
- **Purpose:** Represents a single emotion log record.
- **Fields:** `id` (auto-generated PK), `emotion` (name like "Happy"), `timestamp` (millis when logged).
- **Design:** Public fields for Room compatibility. Constructor takes emotion + timestamp for easy one-line creation.

### EmotionCount
- **Type:** POJO (not a Room entity)
- **Purpose:** Holds results from aggregate queries — an emotion name paired with how many times it was logged.
- **Used by:** `EmoticonDao` aggregate queries, consumed by `SummaryAdapter`.

### EmoticonDao
- **Type:** Room DAO interface (`@Dao`)
- **Purpose:** Defines all database operations for the emotion_log table.
- **Key methods:**
  - `insert()` — adds a new log entry
  - `getAllEntries()` — returns all entries, newest first
  - `getEntriesForDay()` — filters entries by a time range (midnight to midnight)
  - `getEmotionCounts()` / `getEmotionCountsForDay()` — GROUP BY queries returning counts per emotion
  - `deleteAll()` — clears all logs

### EmoticonDatabase
- **Type:** Room Database (`@Database`), extends `RoomDatabase`
- **Purpose:** Singleton access point to the SQLite database.
- **Design:** Uses double-checked locking to ensure only one instance exists app-wide. Provides `emoticonDao()` to get the DAO.

---

## UI Layer — Activities

### MainActivity
- **Purpose:** Main screen with 9 emoticon buttons in a 3×3 grid.
- **Responsibilities:**
  - Handles emotion button clicks — inserts an entry into the DB on a background thread
  - Shows a Toast with the emoji as feedback
  - Navigates to LogActivity and SummaryActivity
- **Design:** Uses `android:onClick` XML attributes. No ActionBar (uses NoActionBar theme).

### LogActivity
- **Purpose:** Displays a chronological list of all logged emotions using a RecyclerView.
- **Responsibilities:**
  - Loads entries from the database on each resume (so new entries show up)
  - Provides a "Clear All" menu option with a confirmation dialog
  - Back button returns to MainActivity
- **Design:** Uses the ActionBar theme for toolbar navigation.

### SummaryActivity
- **Purpose:** Shows a daily summary — emotion counts and frequency percentages for a selected date.
- **Responsibilities:**
  - Defaults to today, with prev/next buttons and a DatePickerDialog for navigation
  - Queries the DB for a specific day (midnight to midnight range)
  - Displays total count and per-emotion breakdown with progress bars
- **Design:** Date range is computed using Calendar. Background thread for DB queries.

---

## UI Layer — Adapters

### LogAdapter
- **Type:** RecyclerView.Adapter
- **Purpose:** Binds `EmoticonEntry` data to card-style rows in LogActivity.
- **Each row shows:** emoji, emotion name, formatted timestamp.
- **Inner class:** `LogViewHolder` — caches view references for recycling efficiency.

### SummaryAdapter
- **Type:** RecyclerView.Adapter
- **Purpose:** Binds `EmotionCount` data to card-style rows in SummaryActivity.
- **Each row shows:** emoji, emotion name, count, frequency %, and a progress bar.
- **Key method:** `setTotalCount()` — called by the activity to enable percentage calculation.
- **Inner class:** `SummaryViewHolder` — caches view references.

---

## Utility

### EmoticonUtils
- **Type:** Final utility class (cannot be instantiated)
- **Purpose:** Centralizes the mapping from emotion names to emoji characters.
- **Key members:**
  - `EMOTION_NAMES[]` — ordered list of all 9 supported emotions
  - `EMOJI_MAP` — HashMap for O(1) emoji lookup
  - `getEmoji(String)` — returns the emoji for a given emotion name

---

## Design Principles
- **Separation of Concerns:** Data layer (Entity, DAO, Database) is separate from UI layer (Activities, Adapters). The utility class handles cross-cutting emoji mapping.
- **Information Hiding:** Database is accessed only through the DAO interface. The singleton pattern in EmoticonDatabase hides the creation logic.
- **Background Threading:** All DB operations run on background threads to keep the UI responsive.
- **Single Responsibility:** Each class has one clear job — e.g., EmotionCount only holds aggregate data, EmoticonUtils only handles emoji mapping.
