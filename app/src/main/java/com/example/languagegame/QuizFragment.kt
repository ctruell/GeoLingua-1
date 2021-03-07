package com.example.languagegame

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlin.random.Random

class QuizFragment : AppCompatActivity() {
    /*var languages: Array<String> = arrayOf("Albanian", "Arabic", "Armenian", "Azerbaijani", "Belarusian", "Bengali", "Bhojpuri", "Bosnian",
        "Bulgarian", "Cantonese", "Croatian", "Czech", "Danish", "Dutch", "English", "Estonian", "Finnish", "French", "Georgian",
        "German", "Greek", "Gujarati", "Hausa", "Hebrew", "Hindi", "Hungarian", "Icelandic", "Indonesian", "Irish", "Italian",
        "Japanese", "Javanese", "Kannada", "Kazakh", "Korean", "Latvian", "Lithuanian", "Macedonian", "Mandarin", "Marathi",
        "Montenegrin", "Norwegian", "Persian", "Polish", "Portuguese", "Punjabi", "Romanian", "Russian", "Serbian", "Slovak", "Slovenian",
        "Spanish", "Swahili", "Swedish", "Tagalog", "Tamil", "Telugu", "Thai", "Turkish", "Ukrainian", "Urdu", "Vietnamese", "Wu (Chinese)")*/

    var phrases: Array<Array<String>> = arrayOf(arrayOf("Mangez bien, riez souvent, aimez beaucoup", "French"),
        arrayOf("Das ist nicht mein Bier", "German"), arrayOf("Kad na vrbi rodi grožđe", "Croatian"),
        arrayOf("Sataa kuin Esterin perseestä", "Finnish"), arrayOf("سَمْن عَلَى عَسَل", "Arabic"), arrayOf("Iku macja, lozin minjtë", "Albanian"),
        arrayOf("Ба́бушка на́двое сказа́ла", "Russian"), arrayOf("Het regent pijpenstelen", "Dutch"),
        arrayOf("An té a bhíónn siúlach, bíonn scéalach", "Irish"), arrayOf("Güle güle gidin", "Turkish"), arrayOf("제 눈에 안경이다", "Korean"),
        arrayOf("Vetřít se někam", "Czech"), arrayOf("Нема ватре без дима", "Serbian"), arrayOf("Ăn miếng trả miếng", "Vietnamese"),
        arrayOf("Habang may buhay, may pag-asa", "Tagalog"), arrayOf("Cine se laudă singur, se ocărăște pe sine", "Romanian"),
        arrayOf("Ukitaka kula nguruwe, kula aliyenona", "Swahili"), arrayOf("Aká matka, taká Katka", "Slovak"),
        arrayOf("A donde el seto es bajo todos pasan", "Spanish"), arrayOf("A roupa suja lava-se em casa", "Portuguese"),
        arrayOf("Curiosity killed the cat", "English"), arrayOf("В каламутній воді легко рибу ловити", "Ukrainian"),
        arrayOf("猿も木から落ちる", "Japanese"), arrayOf("Ἐν οἴνῳ ἀλήθεια", "Greek"), arrayOf("A tiltott gyümölcs a legédesebb", "Hungarian"),
        arrayOf("Tsohon gatarinka, ya hi sari ka ba ni", "Hausa"), arrayOf("Akta dig för fiendegåvor", "Swedish"),
        arrayOf("Chi ama me, ama il mio cane", "Italian"), arrayOf("Dievam trīs lietas patīkamas", "Latvian"), arrayOf("At gå agurk", "Danish"),
        arrayOf("چوری کا مال موری میں", "Urdu"), arrayOf("Каде има сила, нема правдина", "Macedonian"), arrayOf("เพื่อนกินหาง่าย เพื่อนตายหายาก", "Thai"),
        arrayOf("Ուշ լինի, անուշ լինի", "Armenian"), arrayOf("Bahqcinin toruna dusen her bir canli onun baqhdir", "Azerbaijani"),
        arrayOf("Загляне сонца i ў наша ваконца", "Belarusian"), arrayOf("ভাবিয়া করিও কাজ", "Bengali"),
        arrayOf("अबरे क भंइस बियाइल, सगरे गाँव मेटिया ले धाइल", "Bhojpuri"), arrayOf("Pas koji laje ne ujeda", "Bosnian"),
        arrayOf("Приятел в нужда се познава", "Bulgarian"), arrayOf("呃鬼食豆腐", "Cantonese"), arrayOf("Ela ise, lase teisi ka elada", "Estonian"),
        arrayOf("ნიანგის ცრემლები", "Georgian"), arrayOf("ના બોલવા માં નવ ગુણ", "Gujarati"), arrayOf("מה ששנוא עליך אל תעשה לחברך", "Hebrew"),
        arrayOf("जंगल में मोर नाचा किस ने देखा", "Hindi"), arrayOf("Kornbarn, drukkin maðr og dárinn segja sannleikann", "Icelandic"),
        arrayOf("Air tenang jangan disangka tiada buaya", "Indonesian"), arrayOf("Cebol nggayuh lintang", "Javanese"),
        arrayOf("ಆಳಾಗಬಲ್ಲವನು ಅರಸನಾಗಬಲ್ಲ", "Kannada"), arrayOf("Bala jurūdi jıġılūdan űyrenedi", "Kazakh"),
        arrayOf("Kaip senieji giedojo, taip jaunieji dainuoja", "Lithuanian"), arrayOf("读万卷书行万里路", "Mandarin"),
        arrayOf("डॉक्टरला आणि देवाला कधीच नाराज करू नका", "Marathi"), arrayOf("Daleko od očiju daleko od srca", "Montenegrin"),
        arrayOf("Dei er inkje nytt under solen", "Norwegian"), arrayOf("از این گوش میگیرد از ان گوش در میکند", "Persian"),
        arrayOf("Za dziękuję nic się nie kupuje", "Polish"), arrayOf("ਅੰਨ੍ਹੇ ਕੁੱਤੇ,ਹਿਰਨਾਂ ਦੇ ਸ਼ਿਕਾਰੀ", "Punjabi"),
        arrayOf("Lepa beseda lepo mesto najde", "Slovenian"), arrayOf("அகத்தின் அழகு முகத்தில் தெரியும்", "Tamil"),
        arrayOf("కొండ నాలుక కు మందు వేస్తే, ఉన్న నాలుక ఊడిందంట", "Telugu"), arrayOf("只会讲一种外国闲话是不够的", "Wu (Chinese)"))

    var lives = 3
    var score = 0
    var last = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_quiz)

        generateQuiz()
    }

    fun generateQuiz() {
        //reseting the buttons
        if (button1.text != "Button") {
            button1.text = "Button"
            button2.text = "Button"
            button3.text = "Button"
            button4.text = "Button"
        }
        var lidx = phrases.size
        //selecting phrase and checking to see it was not just used
        var p = Random.nextInt(0, lidx)
        if (p == last) {
            p = Random.nextInt(0, lidx)
        }
        last = p
        var ans = Random.nextInt(1, 4)
        var b : Button;

        //adding phrase language to one of the buttons
        if (ans == 1) {
            button1.text = phrases[p][1]
            textView.text = phrases[p][0]
            b = button1
        }
        else if (ans == 2) {
            button2.text = phrases[p][1]
            textView.text = phrases[p][0]
            b = button2
        }
        else if (ans == 3) {
            button3.text = phrases[p][1]
            textView.text = phrases[p][0]
            b = button3
        }
        else if (ans == 4) {
            button4.text = phrases[p][1]
            textView.text = phrases[p][0]
            b = button4
        }
        else print("Error with button")

        //selecting random languages
        var l1 = Random.nextInt(0, lidx)
        var l2 = Random.nextInt(0, lidx)
        var l3 = Random.nextInt(0, lidx)

        //eliminating repeat choices
        if (l1 == p) {
            l1 = Random.nextInt(0, lidx)
        }

        if (l2 == l1 || l2 == p) {
            l2 = Random.nextInt(0, lidx)
        }

        if (l3 == l1 || l3 == l2 || l3 == p) {
            l3 = Random.nextInt(0, lidx)
        }

        //adding languages to remaining buttons
        if (button1.text != "Button") {
            button2.text = phrases[l1][1]
            button3.text = phrases[l2][1]
            button4.text = phrases[l3][1]
        }
        else if (button2.text != "Button") {
            button1.text = phrases[l1][1]
            button3.text = phrases[l2][1]
            button4.text = phrases[l3][1]
        }
        else if (button3.text != "Button") {
            button1.text = phrases[l1][1]
            button2.text = phrases[l2][1]
            button4.text = phrases[l3][1]
        }
        else if (button4.text != "Button") {
            button1.text = phrases[l1][1]
            button2.text = phrases[l2][1]
            button3.text = phrases[l3][1]
        }
        else print("Error")

        //button handlers
        button1.setOnClickListener { view ->
            handleButtonClick(view)
        }
        button2.setOnClickListener { view ->
            handleButtonClick(view)
        }
        button3.setOnClickListener { view ->
            handleButtonClick(view)
        }
        button4.setOnClickListener { view ->
            handleButtonClick(view)
        }
        restartButton.setOnClickListener { view ->
            handleRestartButtonClick(view)
        }
    }

    fun handleButtonClick(view: View) {
        with (view as Button) {
            var choice = arrayOf(textView.text.toString(), view.text.toString())
            //checking to see if answers is correct, if yes increment the score, if no decrement lives, next question
            if (phrases.any { it.contentEquals(choice) }) {
                score++
                scoreNum.text = score.toString()
                generateQuiz()
            }
            else {
                lives--
                livesNum.text = lives.toString()
                //if lives is 0 show game over and restart button
                if (lives <= 0) {
                    textView.text = "Game Over!"
                    //have hidden start over button
                    restartButton.visibility = View.VISIBLE
                }
                else {
                    generateQuiz()
                }
            }
        }
    }

    fun handleRestartButtonClick(view: View) {
        //remove restart button reset lives/score show new question
        restartButton.visibility = View.INVISIBLE
        lives = 3;
        livesNum.text = lives.toString()
        score = 0;
        scoreNum.text = score.toString()
        generateQuiz()
    }
}