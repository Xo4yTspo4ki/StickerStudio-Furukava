package com.stickerstudio.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.*
import android.graphics.Color
import android.view.Gravity

class MainActivity : Activity() {

    private val videoPicker = 100
    private lateinit var videoInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(18, 18, 28)
        window.navigationBarColor = Color.rgb(18, 18, 28)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(24, 35, 24, 24)
        layout.setBackgroundColor(Color.rgb(18, 18, 28))

        val title = TextView(this)
        title.text = "Sticker Studio"
        title.textSize = 30f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER
        layout.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Создавай собственные стикеры"
        subtitle.textSize = 15f
        subtitle.setTextColor(Color.LTGRAY)
        subtitle.gravity = Gravity.CENTER
        layout.addView(subtitle)

        val space = TextView(this)
        space.text = "\n🎬\n"
        space.textSize = 48f
        space.gravity = Gravity.CENTER
        layout.addView(space)

        val selectButton = Button(this)
        selectButton.text = "Выбрать видео"
        layout.addView(selectButton)

        videoInfo = TextView(this)
        videoInfo.text = "Видео ещё не выбрано"
        videoInfo.textSize = 15f
        videoInfo.setTextColor(Color.WHITE)
        videoInfo.gravity = Gravity.CENTER
        layout.addView(videoInfo)

        selectButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "video/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, videoPicker)
        }

        val description = TextView(this)
        description.text = "\n\nТвои инструменты\n\n" +
                "✂️  Подготовка видео\n\n" +
                "🎨  Создание стикеров\n\n" +
                "📦  Наборы стикеров\n\n" +
                "⚙️  Настройки"
        description.textSize = 18f
        description.setTextColor(Color.WHITE)
        layout.addView(description)

        setContentView(layout)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == videoPicker &&
            resultCode == RESULT_OK) {

            val uri: Uri? = data?.data

            if (uri != null) {
                videoInfo.text = "Видео выбрано!\n$uri"
            }
        }
    }
}
