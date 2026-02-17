package com.example.a26100007_emotilog;

import java.util.HashMap;
import java.util.Map;

// Maps emotion names to emoji characters — keeps the mapping in one place
public final class EmoticonUtils {

    // display order for the main screen grid
    public static final String[] EMOTION_NAMES = {
            "Happy", "Sad", "Angry",
            "Grateful", "Excited", "Tired",
            "Anxious", "Calm", "Loved"
    };

    private static final Map<String, String> EMOJI_MAP = new HashMap<>();

    static {
        EMOJI_MAP.put("Happy",    "😊");
        EMOJI_MAP.put("Sad",      "😢");
        EMOJI_MAP.put("Angry",    "😡");
        EMOJI_MAP.put("Grateful", "🙏");
        EMOJI_MAP.put("Excited",  "🤩");
        EMOJI_MAP.put("Tired",    "😴");
        EMOJI_MAP.put("Anxious",  "😰");
        EMOJI_MAP.put("Calm",     "😌");
        EMOJI_MAP.put("Loved",    "🥰");
    }

    public static String getEmoji(String emotion) {
        String emoji = EMOJI_MAP.get(emotion);
        return emoji != null ? emoji : "❓";
    }

    private EmoticonUtils() {} // utility class
}
