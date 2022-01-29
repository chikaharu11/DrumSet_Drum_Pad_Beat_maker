package jp.chikaharu11.instant_drumpad_drums

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.*
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import jp.chikaharu11.instant_drumpad_drums.databinding.ActivityMainBinding
import java.util.*
import kotlin.math.hypot


class MainActivity : AppCompatActivity(), CustomAdapterListener {

    private var mRewardedAd: RewardedAd? = null

    private lateinit var binding: ActivityMainBinding

    private lateinit var adViewContainer: FrameLayout
    private lateinit var admobmAdView: AdView

    private var actionTitle = "rimshot_01".replace("_"," ").uppercase() + " loop"
    private var padText1 = "cymbal_01".replace("_"," ").uppercase()
    private var padText2 = "".replace("_"," ").uppercase()
    private var padText3 = "cymbal_02".replace("_"," ").uppercase()
    private var padText4 = "open_hat_01".replace("_"," ").uppercase()
    private var padText5 = "".replace("_"," ").uppercase()
    private var padText6 = "cymbal_03".replace("_"," ").uppercase()
    private var padText7 = "closed_hat_01".replace("_"," ").uppercase()
    private var padText8 = "tom_02".replace("_"," ").uppercase()
    private var padText9 = "tom_01".replace("_"," ").uppercase()
    private var padText10 = "snare_01".replace("_"," ").uppercase()
    private var padText11 = "kick_01".replace("_"," ").uppercase()
    private var padText12 = "tom_03".replace("_"," ").uppercase()
    private var padText13 = "".replace("_"," ").uppercase()
    private var padText14 = "".replace("_"," ").uppercase()
    private var padText15 = "".replace("_"," ").uppercase()

    private var count = 0.5f
    private var bpm = 1.0f

    private var soundPoolVolume = 0.5f
    private var soundPoolTempo = 1.0f
    private var soundPoolVolume2 = 0.5f
    private var soundPoolTempo2 = 1.0f
    private var soundPoolVolume3 = 0.5f
    private var soundPoolTempo3 = 1.0f
    private var soundPoolVolume4 = 0.5f
    private var soundPoolTempo4 = 1.0f
    private var soundPoolVolume5 = 0.5f
    private var soundPoolTempo5 = 1.0f
    private var soundPoolVolume6 = 0.5f
    private var soundPoolTempo6 = 1.0f
    private var soundPoolVolume7 = 0.5f
    private var soundPoolTempo7 = 1.0f
    private var soundPoolVolume8 = 0.5f
    private var soundPoolTempo8 = 1.0f
    private var soundPoolVolume9 = 0.5f
    private var soundPoolTempo9 = 1.0f
    private var soundPoolVolume10 = 0.5f
    private var soundPoolTempo10 = 1.0f
    private var soundPoolVolume11 = 0.5f
    private var soundPoolTempo11 = 1.0f
    private var soundPoolVolume12 = 0.5f
    private var soundPoolTempo12 = 1.0f
    private var soundPoolVolume13 = 0.5f
    private var soundPoolTempo13 = 1.0f
    private var soundPoolVolume14 = 0.5f
    private var soundPoolTempo14 = 1.0f
    private var soundPoolVolume15 = 0.5f
    private var soundPoolTempo15 = 1.0f

    private val locale: Locale = Locale.getDefault()

    companion object {
        private const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 41
        private const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 42
    }

    @SuppressLint("Range")
    fun selectEX() {
        if (!isReadExternalStoragePermissionGranted()) {
            requestReadExternalStoragePermission()
        } else {
            tSoundList.clear()
            val audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val cursor = contentResolver.query(audioUri, null, null, null, null)
            cursor!!.moveToFirst()
            val path: Array<String?> = arrayOfNulls(cursor.count)
            for (i in path.indices) {
                path[i] = cursor.getString(cursor.getColumnIndex("_data"))
                tSoundList.add(SoundList(path[i].toString()))
                cursor.moveToNext()
            }

            cursor.close()
        }
    }

    private lateinit var soundPool: SoundPool

    private lateinit var mp: MediaPlayer

    private lateinit var lmp: LoopMediaPlayer

    private lateinit var a0CustomAdapter: CustomAdapter
    private lateinit var aCustomAdapter: CustomAdapter
    private lateinit var bCustomAdapter: CustomAdapter
    private lateinit var cCustomAdapter: CustomAdapter
    private lateinit var dCustomAdapter: CustomAdapter
    private lateinit var eCustomAdapter: CustomAdapter
    private lateinit var fCustomAdapter: CustomAdapter
    private lateinit var gCustomAdapter: CustomAdapter
    private lateinit var hCustomAdapter: CustomAdapter
    private lateinit var iCustomAdapter: CustomAdapter
    private lateinit var jCustomAdapter: CustomAdapter
    private lateinit var kCustomAdapter: CustomAdapter
    private lateinit var lCustomAdapter: CustomAdapter
    private lateinit var mCustomAdapter: CustomAdapter
    private lateinit var aaCustomAdapter: CustomAdapter
    private lateinit var abCustomAdapter: CustomAdapter
    private lateinit var acCustomAdapter: CustomAdapter
    private lateinit var adCustomAdapter: CustomAdapter

    private lateinit var nCustomAdapter: CustomAdapter
    private lateinit var oCustomAdapter: CustomAdapter
    private lateinit var pCustomAdapter: CustomAdapter
    private lateinit var qCustomAdapter: CustomAdapter
    private lateinit var rCustomAdapter: CustomAdapter
    private lateinit var baCustomAdapter: CustomAdapter
    private lateinit var bbCustomAdapter: CustomAdapter

    private lateinit var sCustomAdapter: CustomAdapter
    private lateinit var tCustomAdapter: CustomAdapter

    private lateinit var a0SoundList: MutableList<SoundList>
    private lateinit var aSoundList: MutableList<SoundList>
    private lateinit var bSoundList: MutableList<SoundList>
    private lateinit var cSoundList: MutableList<SoundList>
    private lateinit var dSoundList: MutableList<SoundList>
    private lateinit var eSoundList: MutableList<SoundList>
    private lateinit var fSoundList: MutableList<SoundList>
    private lateinit var gSoundList: MutableList<SoundList>
    private lateinit var hSoundList: MutableList<SoundList>
    private lateinit var iSoundList: MutableList<SoundList>
    private lateinit var jSoundList: MutableList<SoundList>
    private lateinit var kSoundList: MutableList<SoundList>
    private lateinit var lSoundList: MutableList<SoundList>
    private lateinit var mSoundList: MutableList<SoundList>
    private lateinit var aaSoundList: MutableList<SoundList>
    private lateinit var abSoundList: MutableList<SoundList>
    private lateinit var acSoundList: MutableList<SoundList>
    private lateinit var adSoundList: MutableList<SoundList>

    private lateinit var nSoundList: MutableList<SoundList>
    private lateinit var oSoundList: MutableList<SoundList>
    private lateinit var pSoundList: MutableList<SoundList>
    private lateinit var qSoundList: MutableList<SoundList>
    private lateinit var rSoundList: MutableList<SoundList>
    private lateinit var baSoundList: MutableList<SoundList>
    private lateinit var bbSoundList: MutableList<SoundList>

    private lateinit var sSoundList: MutableList<SoundList>
    private lateinit var tSoundList: MutableList<SoundList>

    private var sound1 = 0
    private var sound2 = 0
    private var sound3 = 0
    private var sound4 = 0
    private var sound5 = 0
    private var sound6 = 0
    private var sound7 = 0
    private var sound8 = 0
    private var sound9 = 0
    private var sound10 = 0
    private var sound11 = 0
    private var sound12 = 0
    private var sound13 = 0
    private var sound14 = 0
    private var sound15 = 0
    private var sound16 = 0

    private var paste = 0

    private var buttonA = 0
    private var buttonB = 0

    private var adCheck = 0


    @SuppressLint("ClickableViewAccessibility", "SetTextI18n", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
            .apply { setContentView(this.root) }

        stickyImmersiveMode()
        initAdMob()
        loadAdMob()
        loadRewardedAd()

        val orientation = resources.configuration.orientation
        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                binding.textView.text = "cymbal_01".replace("_"," ").uppercase()
                binding.textView2.text = "".replace("_"," ").uppercase()
                binding.textView3.text = "cymbal_02".replace("_"," ").uppercase()
                binding.textView4.text = "open_hat_01".replace("_"," ").uppercase()
                binding.textView5.text = "".replace("_"," ").uppercase()
                binding.textView6.text = "cymbal_03".replace("_"," ").uppercase()
                binding.textView7.text = "closed_hat_01".replace("_"," ").uppercase()
                binding.textView8.text = "tom_02".replace("_"," ").uppercase()
                binding.textView9.text = "tom_01".replace("_"," ").uppercase()
                binding.textView10.text = "snare_01".replace("_"," ").uppercase()
                binding.textView11.text = "kick_01".replace("_"," ").uppercase()
                binding.textView12.text = "tom_03".replace("_"," ").uppercase()
                binding.textView13.text = "".replace("_"," ").uppercase()
                binding.textView14.text = "".replace("_"," ").uppercase()
                binding.textView15.text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText0).text = actionTitle
                findViewById<TextView>(R.id.padText1).text = "cymbal_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText2).text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText3).text = "cymbal_02".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText4).text = "open_hat_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText5).text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText6).text = "cymbal_03".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText7).text = "tom_02".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText8).text = "tom_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText9).text = "mid_tom_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText10).text = "snare_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText11).text = "kick_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText12).text = "tom_03".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText13).text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText14).text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText15).text = "".replace("_"," ").uppercase()
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                padText1 = "cymbal_01".replace("_"," ").uppercase()
                padText2 = "open_hat_01".replace("_"," ").uppercase()
                padText3 = "closed_hat_01".replace("_"," ").uppercase()
                padText4 = "".replace("_"," ").uppercase()
                padText5 = "tom_02".replace("_"," ").uppercase()
                padText6 = "snare_01".replace("_"," ").uppercase()
                padText7 = "".replace("_"," ").uppercase()
                padText8 = "".replace("_"," ").uppercase()
                padText9 = "kick_01".replace("_"," ").uppercase()
                padText10 = "".replace("_"," ").uppercase()
                padText11 = "tom_01".replace("_"," ").uppercase()
                padText12 = "tom_03".replace("_"," ").uppercase()
                padText13 = "cymbal_02".replace("_"," ").uppercase()
                padText14 = "cymbal_03".replace("_"," ").uppercase()
                padText15 = "".replace("_"," ").uppercase()
                binding.textView.text = "cymbal_01".replace("_"," ").uppercase()
                binding.textView2.text = "open_hat_01".replace("_"," ").uppercase()
                binding.textView3.text = "closed_hat_01".replace("_"," ").uppercase()
                binding.textView4.text = "".replace("_"," ").uppercase()
                binding.textView5.text = "tom_02".replace("_"," ").uppercase()
                binding.textView6.text = "snare_01".replace("_"," ").uppercase()
                binding.textView7.text = "".replace("_"," ").uppercase()
                binding.textView8.text = "".replace("_"," ").uppercase()
                binding.textView9.text = "kick_01".replace("_"," ").uppercase()
                binding.textView10.text = "".replace("_"," ").uppercase()
                binding.textView11.text = "tom_01".replace("_"," ").uppercase()
                binding.textView12.text = "tom_03".replace("_"," ").uppercase()
                binding.textView13.text = "cymbal_02".replace("_"," ").uppercase()
                binding.textView14.text = "cymbal_03".replace("_"," ").uppercase()
                binding.textView15.text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText0).text = actionTitle
                findViewById<TextView>(R.id.padText1).text = "cymbal_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText2).text = "open_hat_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText3).text = "closed_hat_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText4).text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText5).text = "tom_02".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText6).text = "snare_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText7).text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText8).text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText9).text = "kick_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText10).text = "".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText11).text = "tom_01".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText12).text = "tom_03".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText13).text = "cymbal_02".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText14).text = "cymbal_03".replace("_"," ").uppercase()
                findViewById<TextView>(R.id.padText15).text = "".replace("_"," ").uppercase()
            }
            Configuration.ORIENTATION_SQUARE -> {
                TODO()
            }
            Configuration.ORIENTATION_UNDEFINED -> {
                TODO()
            }
        }

        val tuning = if (locale == Locale.JAPAN) {
            arrayOf(
                "サウンドの設定をリセット",
                "サウンドの調整",
                "バナー広告を非表示にする",
                "終了する",
                "5x3","5x2",
                "4x3","4x2",
                "3x3","3x2",
                "2x2","2x1"
            ) } else {
            arrayOf(
                "Reset the sound settings",
                "Adjusting Sounds",
                "Hide banner Ads",
                "EXIT",
                "5x3","5x2",
                "4x3","4x2",
                "3x3","3x2",
                "2x2","2x1"
            )
            }
        val adapter = ArrayAdapter(this, R.layout.custom_spinner_dropdown, tuning)
        val gridView: GridView = findViewById(R.id.grid_view)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { adapterView, _, position, _ ->
            when(adapterView.getItemAtPosition(position)) {
                "サウンドの調整" -> {
                    binding.view.visibility = View.VISIBLE
                    gridView.visibility = View.INVISIBLE
                }
                "サウンドの設定をリセット" -> {
                    count = 0.5f
                    bpm = 1.0f
                    soundPoolVolume = 0.5f
                    soundPoolTempo = 1.0f
                    soundPoolVolume2 = 0.5f
                    soundPoolTempo2 = 1.0f
                    soundPoolVolume3 = 0.5f
                    soundPoolTempo3 = 1.0f
                    soundPoolVolume4 = 0.5f
                    soundPoolTempo4 = 1.0f
                    soundPoolVolume5 = 0.5f
                    soundPoolTempo5 = 1.0f
                    soundPoolVolume6 = 0.5f
                    soundPoolTempo6 = 1.0f
                    soundPoolVolume7 = 0.5f
                    soundPoolTempo7 = 1.0f
                    soundPoolVolume8 = 0.5f
                    soundPoolTempo8 = 1.0f
                    soundPoolVolume9 = 0.5f
                    soundPoolTempo9 = 1.0f
                    soundPoolVolume10 = 0.5f
                    soundPoolTempo10 = 1.0f
                    soundPoolVolume11 = 0.5f
                    soundPoolTempo11 = 1.0f
                    soundPoolVolume12 = 0.5f
                    soundPoolTempo12 = 1.0f
                    soundPoolVolume13 = 0.5f
                    soundPoolTempo13 = 1.0f
                    soundPoolVolume14 = 0.5f
                    soundPoolTempo14 = 1.0f
                    soundPoolVolume15 = 0.5f
                    soundPoolTempo15 = 1.0f
                    findViewById<TextView>(R.id.padText0).text = actionTitle
                    findViewById<TextView>(R.id.padText1).text = padText1
                    findViewById<TextView>(R.id.padText2).text = padText2
                    findViewById<TextView>(R.id.padText3).text = padText3
                    findViewById<TextView>(R.id.padText4).text = padText4
                    findViewById<TextView>(R.id.padText5).text = padText5
                    findViewById<TextView>(R.id.padText6).text = padText6
                    findViewById<TextView>(R.id.padText7).text = padText7
                    findViewById<TextView>(R.id.padText8).text = padText8
                    findViewById<TextView>(R.id.padText9).text = padText9
                    findViewById<TextView>(R.id.padText10).text = padText10
                    findViewById<TextView>(R.id.padText11).text = padText11
                    findViewById<TextView>(R.id.padText12).text = padText12
                    findViewById<TextView>(R.id.padText13).text = padText13
                    findViewById<TextView>(R.id.padText14).text = padText14
                    findViewById<TextView>(R.id.padText15).text = padText15
                    binding.gridView.visibility = View.INVISIBLE
                    Toast.makeText(applicationContext, R.string.reset1, Toast.LENGTH_LONG).show()
                }
                "バナー広告を非表示にする" -> {
                    if (adCheck == 0) {
                        AlertDialog.Builder(this)
                            .setOnCancelListener {
                                stickyImmersiveMode()
                            }
                            .setTitle(R.string.menu5a)
                            .setMessage(R.string.menu5b)
                            .setPositiveButton("YES") { _, _ ->
                                showRewardAd()
                            }
                            .setNegativeButton("NO") { _, _ ->
                                stickyImmersiveMode()
                            }
                            .show()
                    } else if (adCheck == 1){
                        AlertDialog.Builder(this)
                            .setOnCancelListener {
                                stickyImmersiveMode()
                            }
                            .setTitle(R.string.menu5c)
                            .setPositiveButton("OK") { _, _ ->
                                stickyImmersiveMode()
                            }
                            .show()
                    }
                }
                "終了する" -> {
                    AlertDialog.Builder(this)
                        .setOnCancelListener {
                            stickyImmersiveMode()
                        }
                        .setTitle(R.string.menu6)
                        .setPositiveButton("YES") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("NO") { _, _ ->
                            stickyImmersiveMode()
                        }
                        .show()
                }
                "Adjusting Sounds" -> {
                    binding.view.visibility = View.VISIBLE
                    gridView.visibility = View.INVISIBLE
                }
                "Reset the sound settings" -> {
                    soundPoolVolume = 0.5f
                    soundPoolTempo = 1.0f
                    soundPoolVolume2 = 0.5f
                    soundPoolTempo2 = 1.0f
                    soundPoolVolume3 = 0.5f
                    soundPoolTempo3 = 1.0f
                    soundPoolVolume4 = 0.5f
                    soundPoolTempo4 = 1.0f
                    soundPoolVolume5 = 0.5f
                    soundPoolTempo5 = 1.0f
                    soundPoolVolume6 = 0.5f
                    soundPoolTempo6 = 1.0f
                    soundPoolVolume7 = 0.5f
                    soundPoolTempo7 = 1.0f
                    soundPoolVolume8 = 0.5f
                    soundPoolTempo8 = 1.0f
                    soundPoolVolume9 = 0.5f
                    soundPoolTempo9 = 1.0f
                    soundPoolVolume10 = 0.5f
                    soundPoolTempo10 = 1.0f
                    soundPoolVolume11 = 0.5f
                    soundPoolTempo11 = 1.0f
                    soundPoolVolume12 = 0.5f
                    soundPoolTempo12 = 1.0f
                    soundPoolVolume13 = 0.5f
                    soundPoolTempo13 = 1.0f
                    soundPoolVolume14 = 0.5f
                    soundPoolTempo14 = 1.0f
                    soundPoolVolume15 = 0.5f
                    soundPoolTempo15 = 1.0f
                    findViewById<TextView>(R.id.padText0).text = actionTitle
                    findViewById<TextView>(R.id.padText1).text = padText1
                    findViewById<TextView>(R.id.padText2).text = padText2
                    findViewById<TextView>(R.id.padText3).text = padText3
                    findViewById<TextView>(R.id.padText4).text = padText4
                    findViewById<TextView>(R.id.padText5).text = padText5
                    findViewById<TextView>(R.id.padText6).text = padText6
                    findViewById<TextView>(R.id.padText7).text = padText7
                    findViewById<TextView>(R.id.padText8).text = padText8
                    findViewById<TextView>(R.id.padText9).text = padText9
                    findViewById<TextView>(R.id.padText10).text = padText10
                    findViewById<TextView>(R.id.padText11).text = padText11
                    findViewById<TextView>(R.id.padText12).text = padText12
                    findViewById<TextView>(R.id.padText13).text = padText13
                    findViewById<TextView>(R.id.padText14).text = padText14
                    findViewById<TextView>(R.id.padText15).text = padText15
                    binding.gridView.visibility = View.INVISIBLE
                    Toast.makeText(applicationContext, R.string.reset1, Toast.LENGTH_LONG).show()
                }
                "Hide banner Ads" -> {
                    if (adCheck == 0) {
                        AlertDialog.Builder(this)
                            .setOnCancelListener {
                                stickyImmersiveMode()
                            }
                            .setTitle(R.string.menu5a)
                            .setMessage(R.string.menu5b)
                            .setPositiveButton("YES") { _, _ ->
                                showRewardAd()
                            }
                            .setNegativeButton("NO") { _, _ ->
                                stickyImmersiveMode()
                            }
                            .show()
                    } else if (adCheck == 1){
                        AlertDialog.Builder(this)
                            .setOnCancelListener {
                                stickyImmersiveMode()
                            }
                            .setTitle(R.string.menu5c)
                            .setPositiveButton("OK") { _, _ ->
                                stickyImmersiveMode()
                            }
                            .show()
                    }
                }
                "EXIT" -> {
                    AlertDialog.Builder(this)
                        .setOnCancelListener {
                            stickyImmersiveMode()
                        }
                        .setTitle(R.string.menu6)
                        .setPositiveButton("YES") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("NO") { _, _ ->
                            stickyImmersiveMode()
                        }
                        .show()
                }
                "5x3" -> {
                    binding.imageView2.visibility = View.VISIBLE
                    binding.imageView5.visibility = View.VISIBLE
                    binding.imageView3.visibility = View.VISIBLE
                    binding.imageView6.visibility = View.VISIBLE
                    binding.imageView7.visibility = View.VISIBLE
                    binding.imageView8.visibility = View.VISIBLE
                    binding.imageView9.visibility = View.VISIBLE
                    binding.imageView10.visibility = View.VISIBLE
                    binding.imageView11.visibility = View.VISIBLE
                    binding.imageView12.visibility = View.VISIBLE
                    binding.imageView13.visibility = View.VISIBLE
                    binding.imageView14.visibility = View.VISIBLE
                    binding.imageView15.visibility = View.VISIBLE
                    binding.backgroundView2.visibility = View.VISIBLE
                    binding.backgroundView5.visibility = View.VISIBLE
                    binding.backgroundView3.visibility = View.VISIBLE
                    binding.backgroundView6.visibility = View.VISIBLE
                    binding.backgroundView7.visibility = View.VISIBLE
                    binding.backgroundView8.visibility = View.VISIBLE
                    binding.backgroundView9.visibility = View.VISIBLE
                    binding.backgroundView10.visibility = View.VISIBLE
                    binding.backgroundView11.visibility = View.VISIBLE
                    binding.backgroundView12.visibility = View.VISIBLE
                    binding.backgroundView13.visibility = View.VISIBLE
                    binding.backgroundView14.visibility = View.VISIBLE
                    binding.backgroundView15.visibility = View.VISIBLE
                    binding.textView2.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                    binding.textView3.visibility = View.VISIBLE
                    binding.textView6.visibility = View.VISIBLE
                    binding.textView7.visibility = View.VISIBLE
                    binding.textView8.visibility = View.VISIBLE
                    binding.textView9.visibility = View.VISIBLE
                    binding.textView10.visibility = View.VISIBLE
                    binding.textView11.visibility = View.VISIBLE
                    binding.textView12.visibility = View.VISIBLE
                    binding.textView13.visibility = View.VISIBLE
                    binding.textView14.visibility = View.VISIBLE
                    binding.textView15.visibility = View.VISIBLE
                    binding.gridView.visibility = View.INVISIBLE
                }
                "4x3" -> {
                    binding.imageView2.visibility = View.VISIBLE
                    binding.imageView5.visibility = View.VISIBLE
                    binding.imageView3.visibility = View.VISIBLE
                    binding.imageView6.visibility = View.VISIBLE
                    binding.imageView7.visibility = View.VISIBLE
                    binding.imageView8.visibility = View.VISIBLE
                    binding.imageView9.visibility = View.VISIBLE
                    binding.imageView10.visibility = View.VISIBLE
                    binding.imageView11.visibility = View.VISIBLE
                    binding.imageView12.visibility = View.VISIBLE
                    binding.imageView13.visibility = View.GONE
                    binding.imageView14.visibility = View.GONE
                    binding.imageView15.visibility = View.GONE
                    binding.backgroundView2.visibility = View.VISIBLE
                    binding.backgroundView5.visibility = View.VISIBLE
                    binding.backgroundView3.visibility = View.VISIBLE
                    binding.backgroundView6.visibility = View.VISIBLE
                    binding.backgroundView7.visibility = View.VISIBLE
                    binding.backgroundView8.visibility = View.VISIBLE
                    binding.backgroundView9.visibility = View.VISIBLE
                    binding.backgroundView10.visibility = View.VISIBLE
                    binding.backgroundView11.visibility = View.VISIBLE
                    binding.backgroundView12.visibility = View.VISIBLE
                    binding.backgroundView13.visibility = View.GONE
                    binding.backgroundView14.visibility = View.GONE
                    binding.backgroundView15.visibility = View.GONE
                    binding.textView2.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                    binding.textView3.visibility = View.VISIBLE
                    binding.textView6.visibility = View.VISIBLE
                    binding.textView7.visibility = View.VISIBLE
                    binding.textView8.visibility = View.VISIBLE
                    binding.textView9.visibility = View.VISIBLE
                    binding.textView10.visibility = View.VISIBLE
                    binding.textView11.visibility = View.VISIBLE
                    binding.textView12.visibility = View.VISIBLE
                    binding.textView13.visibility = View.GONE
                    binding.textView14.visibility = View.GONE
                    binding.textView15.visibility = View.GONE
                    binding.gridView.visibility = View.INVISIBLE
                }
                "3x3" -> {
                    binding.imageView2.visibility = View.VISIBLE
                    binding.imageView5.visibility = View.VISIBLE
                    binding.imageView3.visibility = View.VISIBLE
                    binding.imageView6.visibility = View.VISIBLE
                    binding.imageView7.visibility = View.VISIBLE
                    binding.imageView8.visibility = View.VISIBLE
                    binding.imageView9.visibility = View.VISIBLE
                    binding.imageView10.visibility = View.GONE
                    binding.imageView11.visibility = View.GONE
                    binding.imageView12.visibility = View.GONE
                    binding.imageView13.visibility = View.GONE
                    binding.imageView14.visibility = View.GONE
                    binding.imageView15.visibility = View.GONE
                    binding.backgroundView2.visibility = View.VISIBLE
                    binding.backgroundView5.visibility = View.VISIBLE
                    binding.backgroundView3.visibility = View.VISIBLE
                    binding.backgroundView6.visibility = View.VISIBLE
                    binding.backgroundView7.visibility = View.VISIBLE
                    binding.backgroundView8.visibility = View.VISIBLE
                    binding.backgroundView9.visibility = View.VISIBLE
                    binding.backgroundView10.visibility = View.GONE
                    binding.backgroundView11.visibility = View.GONE
                    binding.backgroundView12.visibility = View.GONE
                    binding.backgroundView13.visibility = View.GONE
                    binding.backgroundView14.visibility = View.GONE
                    binding.backgroundView15.visibility = View.GONE
                    binding.textView2.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                    binding.textView3.visibility = View.VISIBLE
                    binding.textView6.visibility = View.VISIBLE
                    binding.textView7.visibility = View.VISIBLE
                    binding.textView8.visibility = View.VISIBLE
                    binding.textView9.visibility = View.VISIBLE
                    binding.textView10.visibility = View.GONE
                    binding.textView11.visibility = View.GONE
                    binding.textView12.visibility = View.GONE
                    binding.textView13.visibility = View.GONE
                    binding.textView14.visibility = View.GONE
                    binding.textView15.visibility = View.GONE
                    binding.gridView.visibility = View.INVISIBLE
                }
                "5x2" -> {
                    binding.imageView2.visibility = View.VISIBLE
                    binding.imageView5.visibility = View.VISIBLE
                    binding.imageView7.visibility = View.VISIBLE
                    binding.imageView8.visibility = View.VISIBLE
                    binding.imageView10.visibility = View.VISIBLE
                    binding.imageView11.visibility = View.VISIBLE
                    binding.imageView13.visibility = View.VISIBLE
                    binding.imageView14.visibility = View.VISIBLE
                    binding.imageView3.visibility = View.GONE
                    binding.imageView6.visibility = View.GONE
                    binding.imageView9.visibility = View.GONE
                    binding.imageView12.visibility = View.GONE
                    binding.imageView15.visibility = View.GONE
                    binding.backgroundView2.visibility = View.VISIBLE
                    binding.backgroundView5.visibility = View.VISIBLE
                    binding.backgroundView7.visibility = View.VISIBLE
                    binding.backgroundView8.visibility = View.VISIBLE
                    binding.backgroundView10.visibility = View.VISIBLE
                    binding.backgroundView11.visibility = View.VISIBLE
                    binding.backgroundView13.visibility = View.VISIBLE
                    binding.backgroundView14.visibility = View.VISIBLE
                    binding.backgroundView3.visibility = View.GONE
                    binding.backgroundView6.visibility = View.GONE
                    binding.backgroundView9.visibility = View.GONE
                    binding.backgroundView12.visibility = View.GONE
                    binding.backgroundView15.visibility = View.GONE
                    binding.textView2.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                    binding.textView7.visibility = View.VISIBLE
                    binding.textView8.visibility = View.VISIBLE
                    binding.textView10.visibility = View.VISIBLE
                    binding.textView11.visibility = View.VISIBLE
                    binding.textView13.visibility = View.VISIBLE
                    binding.textView14.visibility = View.VISIBLE
                    binding.textView3.visibility = View.GONE
                    binding.textView6.visibility = View.GONE
                    binding.textView9.visibility = View.GONE
                    binding.textView12.visibility = View.GONE
                    binding.textView15.visibility = View.GONE
                    binding.gridView.visibility = View.INVISIBLE
                }
                "4x2" -> {
                    binding.imageView2.visibility = View.VISIBLE
                    binding.imageView5.visibility = View.VISIBLE
                    binding.imageView7.visibility = View.VISIBLE
                    binding.imageView8.visibility = View.VISIBLE
                    binding.imageView10.visibility = View.VISIBLE
                    binding.imageView11.visibility = View.VISIBLE
                    binding.imageView3.visibility = View.GONE
                    binding.imageView6.visibility = View.GONE
                    binding.imageView9.visibility = View.GONE
                    binding.imageView12.visibility = View.GONE
                    binding.imageView13.visibility = View.GONE
                    binding.imageView14.visibility = View.GONE
                    binding.imageView15.visibility = View.GONE
                    binding.backgroundView2.visibility = View.VISIBLE
                    binding.backgroundView5.visibility = View.VISIBLE
                    binding.backgroundView7.visibility = View.VISIBLE
                    binding.backgroundView8.visibility = View.VISIBLE
                    binding.backgroundView10.visibility = View.VISIBLE
                    binding.backgroundView11.visibility = View.VISIBLE
                    binding.backgroundView3.visibility = View.GONE
                    binding.backgroundView6.visibility = View.GONE
                    binding.backgroundView9.visibility = View.GONE
                    binding.backgroundView12.visibility = View.GONE
                    binding.backgroundView13.visibility = View.GONE
                    binding.backgroundView14.visibility = View.GONE
                    binding.backgroundView15.visibility = View.GONE
                    binding.textView2.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                    binding.textView7.visibility = View.VISIBLE
                    binding.textView8.visibility = View.VISIBLE
                    binding.textView10.visibility = View.VISIBLE
                    binding.textView11.visibility = View.VISIBLE
                    binding.textView3.visibility = View.GONE
                    binding.textView6.visibility = View.GONE
                    binding.textView9.visibility = View.GONE
                    binding.textView12.visibility = View.GONE
                    binding.textView13.visibility = View.GONE
                    binding.textView14.visibility = View.GONE
                    binding.textView15.visibility = View.GONE
                    binding.gridView.visibility = View.INVISIBLE
                }
                "3x2" -> {
                    binding.imageView2.visibility = View.VISIBLE
                    binding.imageView5.visibility = View.VISIBLE
                    binding.imageView7.visibility = View.VISIBLE
                    binding.imageView8.visibility = View.VISIBLE
                    binding.imageView3.visibility = View.GONE
                    binding.imageView6.visibility = View.GONE
                    binding.imageView9.visibility = View.GONE
                    binding.imageView10.visibility = View.GONE
                    binding.imageView11.visibility = View.GONE
                    binding.imageView12.visibility = View.GONE
                    binding.imageView13.visibility = View.GONE
                    binding.imageView14.visibility = View.GONE
                    binding.imageView15.visibility = View.GONE
                    binding.backgroundView2.visibility = View.VISIBLE
                    binding.backgroundView5.visibility = View.VISIBLE
                    binding.backgroundView7.visibility = View.VISIBLE
                    binding.backgroundView8.visibility = View.VISIBLE
                    binding.backgroundView3.visibility = View.GONE
                    binding.backgroundView6.visibility = View.GONE
                    binding.backgroundView9.visibility = View.GONE
                    binding.backgroundView10.visibility = View.GONE
                    binding.backgroundView11.visibility = View.GONE
                    binding.backgroundView12.visibility = View.GONE
                    binding.backgroundView13.visibility = View.GONE
                    binding.backgroundView14.visibility = View.GONE
                    binding.backgroundView15.visibility = View.GONE
                    binding.textView2.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                    binding.textView7.visibility = View.VISIBLE
                    binding.textView8.visibility = View.VISIBLE
                    binding.textView3.visibility = View.GONE
                    binding.textView6.visibility = View.GONE
                    binding.textView9.visibility = View.GONE
                    binding.textView10.visibility = View.GONE
                    binding.textView11.visibility = View.GONE
                    binding.textView12.visibility = View.GONE
                    binding.textView13.visibility = View.GONE
                    binding.textView14.visibility = View.GONE
                    binding.textView15.visibility = View.GONE
                    binding.gridView.visibility = View.INVISIBLE
                }
                "2x2" -> {
                    binding.imageView2.visibility = View.VISIBLE
                    binding.imageView5.visibility = View.VISIBLE
                    binding.imageView3.visibility = View.GONE
                    binding.imageView6.visibility = View.GONE
                    binding.imageView7.visibility = View.GONE
                    binding.imageView8.visibility = View.GONE
                    binding.imageView9.visibility = View.GONE
                    binding.imageView10.visibility = View.GONE
                    binding.imageView11.visibility = View.GONE
                    binding.imageView12.visibility = View.GONE
                    binding.imageView13.visibility = View.GONE
                    binding.imageView14.visibility = View.GONE
                    binding.imageView15.visibility = View.GONE
                    binding.backgroundView2.visibility = View.VISIBLE
                    binding.backgroundView5.visibility = View.VISIBLE
                    binding.backgroundView3.visibility = View.GONE
                    binding.backgroundView6.visibility = View.GONE
                    binding.backgroundView7.visibility = View.GONE
                    binding.backgroundView8.visibility = View.GONE
                    binding.backgroundView9.visibility = View.GONE
                    binding.backgroundView10.visibility = View.GONE
                    binding.backgroundView11.visibility = View.GONE
                    binding.backgroundView12.visibility = View.GONE
                    binding.backgroundView13.visibility = View.GONE
                    binding.backgroundView14.visibility = View.GONE
                    binding.backgroundView15.visibility = View.GONE
                    binding.textView2.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                    binding.textView3.visibility = View.GONE
                    binding.textView6.visibility = View.GONE
                    binding.textView7.visibility = View.GONE
                    binding.textView8.visibility = View.GONE
                    binding.textView9.visibility = View.GONE
                    binding.textView10.visibility = View.GONE
                    binding.textView11.visibility = View.GONE
                    binding.textView12.visibility = View.GONE
                    binding.textView13.visibility = View.GONE
                    binding.textView14.visibility = View.GONE
                    binding.textView15.visibility = View.GONE
                    binding.gridView.visibility = View.INVISIBLE
                }
                "2x1" -> {
                    binding.imageView2.visibility = View.GONE
                    binding.imageView3.visibility = View.GONE
                    binding.imageView5.visibility = View.GONE
                    binding.imageView6.visibility = View.GONE
                    binding.imageView7.visibility = View.GONE
                    binding.imageView8.visibility = View.GONE
                    binding.imageView9.visibility = View.GONE
                    binding.imageView10.visibility = View.GONE
                    binding.imageView11.visibility = View.GONE
                    binding.imageView12.visibility = View.GONE
                    binding.imageView13.visibility = View.GONE
                    binding.imageView14.visibility = View.GONE
                    binding.imageView15.visibility = View.GONE
                    binding.backgroundView2.visibility = View.GONE
                    binding.backgroundView3.visibility = View.GONE
                    binding.backgroundView5.visibility = View.GONE
                    binding.backgroundView6.visibility = View.GONE
                    binding.backgroundView7.visibility = View.GONE
                    binding.backgroundView8.visibility = View.GONE
                    binding.backgroundView9.visibility = View.GONE
                    binding.backgroundView10.visibility = View.GONE
                    binding.backgroundView11.visibility = View.GONE
                    binding.backgroundView12.visibility = View.GONE
                    binding.backgroundView13.visibility = View.GONE
                    binding.backgroundView14.visibility = View.GONE
                    binding.backgroundView15.visibility = View.GONE
                    binding.textView2.visibility = View.GONE
                    binding.textView3.visibility = View.GONE
                    binding.textView5.visibility = View.GONE
                    binding.textView6.visibility = View.GONE
                    binding.textView7.visibility = View.GONE
                    binding.textView8.visibility = View.GONE
                    binding.textView9.visibility = View.GONE
                    binding.textView10.visibility = View.GONE
                    binding.textView11.visibility = View.GONE
                    binding.textView12.visibility = View.GONE
                    binding.textView13.visibility = View.GONE
                    binding.textView14.visibility = View.GONE
                    binding.textView15.visibility = View.GONE
                    binding.gridView.visibility = View.INVISIBLE
                }
            }
        }

        val choose = if (locale == Locale.JAPAN) {
            arrayOf(
                "ドラムパッドをリセット",
                "演奏モード⇔パッド音の変更",
                "Metronome loops",
                "Bass loops",
                "Drums loops",
                "Guitar loops",
                "Keys loops",
                "Orch loops",
                "Percussion loops",
                "Synth loops",
                "External sound loops"
            ) } else {
            arrayOf(
                "Reset drum pads",
                "Play mode ↔ Change pad sounds",
                "Metronome loops",
                "Bass loops",
                "Drums loops",
                "Guitar loops",
                "Keys loops",
                "Orch loops",
                "Percussion loops",
                "Synth loops",
                "External sound loops"
            )
        }
        val adapter2 = ArrayAdapter(this, R.layout.custom_spinner_dropdown, choose)
        val gridView2: GridView = findViewById(R.id.grid_view_choose)
        val soundListView = findViewById<ListView>(R.id.list_view)
        gridView2.adapter = adapter2

        gridView2.setOnItemClickListener { adapterView, _, position, _ ->
            when (adapterView.getItemAtPosition(position)) {
                "演奏モード⇔パッド音の変更" -> {
                    when (paste) {
                        0 -> {
                            paste = 1
                            invalidateOptionsMenu()
                            Toast.makeText(applicationContext, R.string.change, Toast.LENGTH_LONG).show()
                            gridView2.visibility = View.INVISIBLE
                        }
                        1 -> {
                            paste = 0
                            invalidateOptionsMenu()
                            Toast.makeText(applicationContext, R.string.change2, Toast.LENGTH_LONG).show()
                            gridView2.visibility = View.INVISIBLE
                        }
                    }
                }
                "ドラムパッドをリセット" -> {
                    binding.textView.text = ""
                    binding.textView2.text = ""
                    binding.textView3.text = ""
                    binding.textView4.text = ""
                    binding.textView5.text = ""
                    binding.textView6.text = ""
                    binding.textView7.text = ""
                    binding.textView8.text = ""
                    binding.textView9.text = ""
                    binding.textView10.text = ""
                    binding.textView11.text = ""
                    binding.textView12.text = ""
                    binding.textView13.text = ""
                    binding.textView14.text = ""
                    binding.textView15.text = ""
                    findViewById<TextView>(R.id.padText1).text = ""
                    findViewById<TextView>(R.id.padText2).text = ""
                    findViewById<TextView>(R.id.padText3).text = ""
                    findViewById<TextView>(R.id.padText4).text = ""
                    findViewById<TextView>(R.id.padText5).text = ""
                    findViewById<TextView>(R.id.padText6).text = ""
                    findViewById<TextView>(R.id.padText7).text = ""
                    findViewById<TextView>(R.id.padText8).text = ""
                    findViewById<TextView>(R.id.padText9).text = ""
                    findViewById<TextView>(R.id.padText10).text = ""
                    findViewById<TextView>(R.id.padText11).text = ""
                    findViewById<TextView>(R.id.padText12).text = ""
                    findViewById<TextView>(R.id.padText13).text = ""
                    findViewById<TextView>(R.id.padText14).text = ""
                    findViewById<TextView>(R.id.padText15).text = ""
                    padText1 = ""
                    padText2 = ""
                    padText3 = ""
                    padText4 = ""
                    padText5 = ""
                    padText6 = ""
                    padText7 = ""
                    padText8 = ""
                    padText9 = ""
                    padText10 = ""
                    padText11 = ""
                    padText12 = ""
                    padText13 = ""
                    padText14 = ""
                    padText15 = ""
                    count = 0.5f
                    bpm = 1.0f
                    soundPoolVolume = 0.5f
                    soundPoolTempo = 1.0f
                    soundPoolVolume2 = 0.5f
                    soundPoolTempo2 = 1.0f
                    soundPoolVolume3 = 0.5f
                    soundPoolTempo3 = 1.0f
                    soundPoolVolume4 = 0.5f
                    soundPoolTempo4 = 1.0f
                    soundPoolVolume5 = 0.5f
                    soundPoolTempo5 = 1.0f
                    soundPoolVolume6 = 0.5f
                    soundPoolTempo6 = 1.0f
                    soundPoolVolume7 = 0.5f
                    soundPoolTempo7 = 1.0f
                    soundPoolVolume8 = 0.5f
                    soundPoolTempo8 = 1.0f
                    soundPoolVolume9 = 0.5f
                    soundPoolTempo9 = 1.0f
                    soundPoolVolume10 = 0.5f
                    soundPoolTempo10 = 1.0f
                    soundPoolVolume11 = 0.5f
                    soundPoolTempo11 = 1.0f
                    soundPoolVolume12 = 0.5f
                    soundPoolTempo12 = 1.0f
                    soundPoolVolume13 = 0.5f
                    soundPoolTempo13 = 1.0f
                    soundPoolVolume14 = 0.5f
                    soundPoolTempo14 = 1.0f
                    soundPoolVolume15 = 0.5f
                    soundPoolTempo15 = 1.0f
                    sound1 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound2 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound3 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound4 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound5 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound6 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound7 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound8 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound9 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound10 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound11 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound12 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound13 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound14 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound15 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound16 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    gridView2.visibility = View.INVISIBLE
                    Toast.makeText(applicationContext, R.string.reset2, Toast.LENGTH_LONG).show()
                }
                "Play mode ↔ Change pad sounds" -> {
                    when (paste) {
                        0 -> {
                            paste = 1
                            invalidateOptionsMenu()
                            Toast.makeText(applicationContext, R.string.change, Toast.LENGTH_LONG).show()
                            gridView2.visibility = View.INVISIBLE
                        }
                        1 -> {
                            paste = 0
                            invalidateOptionsMenu()
                            Toast.makeText(applicationContext, R.string.change2, Toast.LENGTH_LONG).show()
                            gridView2.visibility = View.INVISIBLE
                        }
                    }
                }
                "Metronome loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 2
                    soundListView.adapter = tCustomAdapter
                    tCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }
                "Bass loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 2
                    soundListView.adapter = nCustomAdapter
                    nCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }
                "Drums loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 2
                    soundListView.adapter = oCustomAdapter
                    oCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }
                "Guitar loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 2
                    soundListView.adapter = pCustomAdapter
                    pCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }
                "Keys loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 2
                    soundListView.adapter = qCustomAdapter
                    qCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }
                "Orch loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 2
                    soundListView.adapter = rCustomAdapter
                    rCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }
                "Percussion loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 2
                    soundListView.adapter = baCustomAdapter
                    baCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }
                "Synth loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 2
                    soundListView.adapter = bbCustomAdapter
                    bbCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }

                "External sound Loops" -> {
                    lmp.stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                    buttonA = 16
                    buttonB = 1
                    selectEX()
                    soundListView.adapter = tCustomAdapter
                    tCustomAdapter.notifyDataSetChanged()
                    soundListView.visibility = View.VISIBLE
                    gridView2.visibility = View.INVISIBLE
                }
                "Reset drum pads" -> {
                    binding.textView.text = ""
                    binding.textView2.text = ""
                    binding.textView3.text = ""
                    binding.textView4.text = ""
                    binding.textView5.text = ""
                    binding.textView6.text = ""
                    binding.textView7.text = ""
                    binding.textView8.text = ""
                    binding.textView9.text = ""
                    binding.textView10.text = ""
                    binding.textView11.text = ""
                    binding.textView12.text = ""
                    binding.textView13.text = ""
                    binding.textView14.text = ""
                    binding.textView15.text = ""
                    findViewById<TextView>(R.id.padText1).text = ""
                    findViewById<TextView>(R.id.padText2).text = ""
                    findViewById<TextView>(R.id.padText3).text = ""
                    findViewById<TextView>(R.id.padText4).text = ""
                    findViewById<TextView>(R.id.padText5).text = ""
                    findViewById<TextView>(R.id.padText6).text = ""
                    findViewById<TextView>(R.id.padText7).text = ""
                    findViewById<TextView>(R.id.padText8).text = ""
                    findViewById<TextView>(R.id.padText9).text = ""
                    findViewById<TextView>(R.id.padText10).text = ""
                    findViewById<TextView>(R.id.padText11).text = ""
                    findViewById<TextView>(R.id.padText12).text = ""
                    findViewById<TextView>(R.id.padText13).text = ""
                    findViewById<TextView>(R.id.padText14).text = ""
                    findViewById<TextView>(R.id.padText15).text = ""
                    padText1 = ""
                    padText2 = ""
                    padText3 = ""
                    padText4 = ""
                    padText5 = ""
                    padText6 = ""
                    padText7 = ""
                    padText8 = ""
                    padText9 = ""
                    padText10 = ""
                    padText11 = ""
                    padText12 = ""
                    padText13 = ""
                    padText14 = ""
                    padText15 = ""
                    count = 0.5f
                    bpm = 1.0f
                    soundPoolVolume = 0.5f
                    soundPoolTempo = 1.0f
                    soundPoolVolume2 = 0.5f
                    soundPoolTempo2 = 1.0f
                    soundPoolVolume3 = 0.5f
                    soundPoolTempo3 = 1.0f
                    soundPoolVolume4 = 0.5f
                    soundPoolTempo4 = 1.0f
                    soundPoolVolume5 = 0.5f
                    soundPoolTempo5 = 1.0f
                    soundPoolVolume6 = 0.5f
                    soundPoolTempo6 = 1.0f
                    soundPoolVolume7 = 0.5f
                    soundPoolTempo7 = 1.0f
                    soundPoolVolume8 = 0.5f
                    soundPoolTempo8 = 1.0f
                    soundPoolVolume9 = 0.5f
                    soundPoolTempo9 = 1.0f
                    soundPoolVolume10 = 0.5f
                    soundPoolTempo10 = 1.0f
                    soundPoolVolume11 = 0.5f
                    soundPoolTempo11 = 1.0f
                    soundPoolVolume12 = 0.5f
                    soundPoolTempo12 = 1.0f
                    soundPoolVolume13 = 0.5f
                    soundPoolTempo13 = 1.0f
                    soundPoolVolume14 = 0.5f
                    soundPoolTempo14 = 1.0f
                    soundPoolVolume15 = 0.5f
                    soundPoolTempo15 = 1.0f
                    sound1 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound2 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound3 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound4 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound5 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound6 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound7 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound8 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound9 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound10 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound11 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound12 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound13 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound14 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound15 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    sound16 = soundPool.load(assets.openFd("soundless.ogg"), 1)
                    gridView2.visibility = View.INVISIBLE
                    Toast.makeText(applicationContext, R.string.reset2, Toast.LENGTH_LONG).show()
                }
            }
        }


        a0SoundList = arrayListOf(
            SoundList("cymbal_01.ogg"),
            SoundList("cymbal_02.ogg"),
            SoundList("cymbal_03.ogg"),
            SoundList("open_hat_01.ogg"),
            SoundList("open_hat_02.ogg"),
            SoundList("open_hat_03.ogg"),
            SoundList("closed_hat_01.ogg"),
            SoundList("closed_hat_02.ogg"),
            SoundList("closed_hat_03.ogg"),
            SoundList("closed_hat_04.ogg"),
            SoundList("closed_hat_05.ogg"),
            SoundList("kick_01.ogg"),
            SoundList("kick_02.ogg"),
            SoundList("kick_03.ogg"),
            SoundList("kick_04.ogg"),
            SoundList("snare_01.ogg"),
            SoundList("snare_02.ogg"),
            SoundList("snare_03.ogg"),
            SoundList("snare_04.ogg"),
            SoundList("tom_01.ogg"),
            SoundList("tom_02.ogg"),
            SoundList("tom_03.ogg")
        )

        aSoundList = arrayListOf(
            SoundList("cycdh_crash_01.ogg"),
            SoundList("cycdh_crash_02.ogg"),
            SoundList("cycdh_crash_03.ogg"),
            SoundList("cycdh_multicrash_01.ogg"),
            SoundList("cycdh_multicrash_02.ogg"),
            SoundList("cycdh_multicrash_03.ogg"),
            SoundList("cycdh_multicrashhi_01.ogg"),
            SoundList("cycdh_multicrashhi_02.ogg"),
            SoundList("cycdh_multicrashhi_03.ogg"),
            SoundList("cycdh_multicrashlo_01.ogg"),
            SoundList("cycdh_multicrashlo_02.ogg"),
            SoundList("cycdh_multicrashlo_03.ogg")
        )

        bSoundList = arrayListOf(
            SoundList("cycdh_trasha_01.ogg"),
            SoundList("cycdh_trasha_02.ogg"),
            SoundList("cycdh_trasha_03.ogg"),
            SoundList("cycdh_trasha_04.ogg"),
            SoundList("cycdh_trasha_05.ogg"),
            SoundList("cycdh_trasha_06.ogg"),
            SoundList("cycdh_trasha_07.ogg"),
            SoundList("cycdh_trasha_08.ogg"),
            SoundList("cycdh_trasha_09.ogg"),
            SoundList("cycdh_trashb_01.ogg"),
            SoundList("cycdh_trashb_02.ogg"),
            SoundList("cycdh_trashb_03.ogg"),
            SoundList("cycdh_trashc_01.ogg"),
            SoundList("cycdh_trashc_02.ogg"),
            SoundList("cycdh_trashc_03.ogg"),
            SoundList("cycdh_trashc_04.ogg"),
            SoundList("cycdh_trashd_01.ogg"),
            SoundList("cycdh_trashd_02.ogg"),
            SoundList("cycdh_trashe_01.ogg"),
            SoundList("cycdh_trashe_02.ogg"),
            SoundList("cycdh_trashe_03.ogg"),
            SoundList("cycdh_trashe_04.ogg"),
            SoundList("cycdh_trashe_05.ogg"),
            SoundList("cycdh_trashf_01.ogg"),
            SoundList("cycdh_trashf_02.ogg"),
            SoundList("cycdh_trashf_03.ogg"),
            SoundList("cycdh_trashf_04.ogg")
        )
        cSoundList = arrayListOf(
            SoundList("acoustic_hat_01.ogg"),
            SoundList("acoustic_hat_02.ogg"),
            SoundList("acoustic_hat_03.ogg"),
            SoundList("acoustic_hat_04.ogg"),
            SoundList("acoustic_hat_05.ogg"),
            SoundList("acoustic_hat_06.ogg")
        )
        dSoundList = arrayListOf(
            SoundList("cycdh_sab_clhat_01.ogg"),
            SoundList("cycdh_sab_clhat_02.ogg"),
            SoundList("cycdh_sab_clhat_03.ogg"),
            SoundList("cycdh_sab_clhat_04.ogg"),
            SoundList("cycdh_sab_clhat_05.ogg"),
            SoundList("cycdh_sab_clhat_06.ogg"),
            SoundList("cycdh_sab_clhat_07.ogg"),
            SoundList("cycdh_sab_clhat_08.ogg"),
            SoundList("cycdh_sab_clhat_09.ogg"),
            SoundList("cycdh_sab_clhat_10.ogg"),
            SoundList("cycdh_sab_clhat_11.ogg"),
            SoundList("cycdh_sab_clhat_12.ogg"),
            SoundList("cycdh_sab_clhat_13.ogg"),
            SoundList("cycdh_sab_clhat_14.ogg"),
            SoundList("cycdh_sab_hfhat_01.ogg"),
            SoundList("cycdh_sab_hfhat_02.ogg"),
            SoundList("cycdh_sab_hfhat_03.ogg"),
            SoundList("cycdh_sab_hfhat_04.ogg"),
            SoundList("cycdh_sab_ophat_01.ogg"),
            SoundList("cycdh_sab_ophat_02.ogg"),
            SoundList("cycdh_sab_ophat_03.ogg"),
            SoundList("cycdh_sab_ophat_04.ogg"),
            SoundList("cycdh_sab_ophat_05.ogg"),
            SoundList("cycdh_sab_ophat_06.ogg"),
            SoundList("cycdh_sab_ophat_07.ogg"),
            SoundList("cycdh_sab_ophat_08.ogg"),
            SoundList("cycdh_sab_ophat_09.ogg"),
            SoundList("cycdh_sab_pdhat_01.ogg"),
            SoundList("cycdh_sab_pdhat_02.ogg")
        )
        eSoundList = arrayListOf(
            SoundList("khats_clsd_01.ogg"),
            SoundList("khats_clsd_02.ogg"),
            SoundList("khats_clsd_03.ogg"),
            SoundList("khats_clsd_04.ogg"),
            SoundList("khats_clsd_05.ogg"),
            SoundList("khats_clsd_06.ogg"),
            SoundList("khats_clsd_07.ogg"),
            SoundList("khats_clsd_08.ogg"),
            SoundList("khats_clsd_09.ogg"),
            SoundList("khats_clsd_10.ogg"),
            SoundList("khats_clsd_11.ogg"),
            SoundList("khats_hlfop_01.ogg"),
            SoundList("khats_hlfop_02.ogg"),
            SoundList("khats_hlfop_03.ogg"),
            SoundList("khats_open_01.ogg"),
            SoundList("khats_open_02.ogg"),
            SoundList("khats_open_03.ogg"),
            SoundList("khats_open_04.ogg"),
            SoundList("khats_open_05.ogg"),
            SoundList("khats_open_06.ogg"),
            SoundList("khats_open_07.ogg"),
            SoundList("khats_open_08.ogg"),
            SoundList("khats_open_09.ogg"),
            SoundList("khats_pdl_01.ogg"),
            SoundList("khats_pdl_02.ogg"),
            SoundList("khats_pdl_03.ogg"),
            SoundList("khats_pdl_04.ogg")
        )
        fSoundList = arrayListOf(
            SoundList("cycdh_acoukick_01.ogg"),
            SoundList("cycdh_acoukick_02.ogg"),
            SoundList("cycdh_acoukick_03.ogg"),
            SoundList("cycdh_acoukick_04.ogg"),
            SoundList("cycdh_acoukick_05.ogg"),
            SoundList("cycdh_acoukick_06.ogg"),
            SoundList("cycdh_acoukick_07.ogg"),
            SoundList("cycdh_acoukick_08.ogg"),
            SoundList("cycdh_acoukick_09.ogg"),
            SoundList("cycdh_acoukick_10.ogg"),
            SoundList("cycdh_acoukick_11.ogg"),
            SoundList("cycdh_acoukick_12.ogg"),
            SoundList("cycdh_acoukick_13.ogg"),
            SoundList("cycdh_acoukick_14.ogg"),
            SoundList("cycdh_acoukick_15.ogg"),
            SoundList("cycdh_acoukick_16.ogg"),
            SoundList("cycdh_acoukick_17.ogg"),
            SoundList("cycdh_acoukick_18.ogg"),
            SoundList("cycdh_acoukick_19.ogg"),
            SoundList("cycdh_acoukick_20.ogg")
        )
        gSoundList = arrayListOf(
            SoundList("cycdh_keskick_01.ogg"),
            SoundList("cycdh_keskick_02.ogg"),
            SoundList("cycdh_keskick_03.ogg"),
            SoundList("cycdh_keskick_04.ogg"),
            SoundList("cycdh_keskick_05.ogg"),
            SoundList("cycdh_keskick_06.ogg"),
            SoundList("cycdh_keskick_07.ogg"),
            SoundList("cycdh_keskick_08.ogg")
        )
        hSoundList = arrayListOf(
            SoundList("cycdh_loosekick_01.ogg"),
            SoundList("cycdh_loosekick_02.ogg"),
            SoundList("cycdh_loosekick_03.ogg"),
            SoundList("cycdh_loosekick_04.ogg"),
            SoundList("cycdh_loosekick_05.ogg"),
            SoundList("cycdh_loosekick_06.ogg"),
            SoundList("cycdh_loosekick_07.ogg"),
            SoundList("cycdh_loosekick_08.ogg")
        )
        iSoundList = arrayListOf(
            SoundList("acoustic_snare_01.ogg"),
            SoundList("acoustic_snare_02.ogg"),
            SoundList("acoustic_snare_03.ogg"),
            SoundList("acoustic_snare_04.ogg")
        )
        jSoundList = arrayListOf(
            SoundList("cycdh_ludflama_01.ogg"),
            SoundList("cycdh_ludflama_02.ogg"),
            SoundList("cycdh_ludflama_03.ogg"),
            SoundList("cycdh_ludflama_04.ogg"),
            SoundList("cycdh_ludflama_05.ogg"),
            SoundList("cycdh_ludrima_01.ogg"),
            SoundList("cycdh_ludrima_02.ogg"),
            SoundList("cycdh_ludrima_03.ogg"),
            SoundList("cycdh_ludrima_04.ogg"),
            SoundList("cycdh_ludrima_05.ogg"),
            SoundList("cycdh_ludrima_06.ogg"),
            SoundList("cycdh_ludrima_07.ogg"),
            SoundList("cycdh_ludsdsta_01.ogg"),
            SoundList("cycdh_ludsdsta_02.ogg"),
            SoundList("cycdh_ludsdsta_03.ogg"),
            SoundList("cycdh_ludsdsta_04.ogg"),
            SoundList("cycdh_ludsdsta_05.ogg"),
            SoundList("cycdh_ludsdsta_06.ogg"),
            SoundList("cycdh_ludsdsta_07.ogg"),
            SoundList("cycdh_ludsnra_01.ogg"),
            SoundList("cycdh_ludsnra_02.ogg"),
            SoundList("cycdh_ludsnra_03.ogg"),
            SoundList("cycdh_ludsnra_04.ogg"),
            SoundList("cycdh_ludsnra_05.ogg"),
            SoundList("cycdh_ludsnroffa_01.ogg"),
            SoundList("cycdh_ludsnroffa_02.ogg"),
            SoundList("cycdh_ludsnroffa_03.ogg"),
            SoundList("cycdh_ludsnroffa_04.ogg"),
            SoundList("cycdh_ludsnroffa_05.ogg"),
            SoundList("cycdh_ludsnroffa_06.ogg"),
            SoundList("cycdh_ludsnroffa_07.ogg"),
            SoundList("cycdh_ludsnroffa_08.ogg")
        )
        kSoundList = arrayListOf(
            SoundList("cycdh_ludflamb_01.ogg"),
            SoundList("cycdh_ludflamb_02.ogg"),
            SoundList("cycdh_ludflamb_03.ogg"),
            SoundList("cycdh_ludflamb_04.ogg"),
            SoundList("cycdh_ludflamb_05.ogg"),
            SoundList("cycdh_ludrimb_01.ogg"),
            SoundList("cycdh_ludrimb_02.ogg"),
            SoundList("cycdh_ludrimb_03.ogg"),
            SoundList("cycdh_ludrimb_04.ogg"),
            SoundList("cycdh_ludrimb_05.ogg"),
            SoundList("cycdh_ludrimb_06.ogg"),
            SoundList("cycdh_ludrimb_07.ogg"),
            SoundList("cycdh_ludsdstb_01.ogg"),
            SoundList("cycdh_ludsdstb_02.ogg"),
            SoundList("cycdh_ludsdstb_03.ogg"),
            SoundList("cycdh_ludsdstb_04.ogg"),
            SoundList("cycdh_ludsdstb_05.ogg"),
            SoundList("cycdh_ludsdstb_06.ogg"),
            SoundList("cycdh_ludsdstb_07.ogg"),
            SoundList("cycdh_ludsnrb_01.ogg"),
            SoundList("cycdh_ludsnrb_02.ogg"),
            SoundList("cycdh_ludsnrb_03.ogg"),
            SoundList("cycdh_ludsnrb_04.ogg"),
            SoundList("cycdh_ludsnrb_05.ogg"),
            SoundList("cycdh_ludsnroffb_01.ogg"),
            SoundList("cycdh_ludsnroffb_02.ogg"),
            SoundList("cycdh_ludsnroffb_03.ogg"),
            SoundList("cycdh_ludsnroffb_04.ogg"),
            SoundList("cycdh_ludsnroffb_05.ogg"),
            SoundList("cycdh_ludsnroffb_06.ogg"),
            SoundList("cycdh_ludsnroffb_07.ogg"),
            SoundList("cycdh_ludsnroffb_08.ogg")
        )
        lSoundList = arrayListOf(
            SoundList("cycdh_ludflamc_01.ogg"),
            SoundList("cycdh_ludflamc_02.ogg"),
            SoundList("cycdh_ludflamc_03.ogg"),
            SoundList("cycdh_ludflamc_04.ogg"),
            SoundList("cycdh_ludflamc_05.ogg"),
            SoundList("cycdh_ludrimc_01.ogg"),
            SoundList("cycdh_ludrimc_02.ogg"),
            SoundList("cycdh_ludrimc_03.ogg"),
            SoundList("cycdh_ludrimc_04.ogg"),
            SoundList("cycdh_ludrimc_05.ogg"),
            SoundList("cycdh_ludrimc_06.ogg"),
            SoundList("cycdh_ludrimc_07.ogg"),
            SoundList("cycdh_ludsdstc_01.ogg"),
            SoundList("cycdh_ludsdstc_02.ogg"),
            SoundList("cycdh_ludsdstc_03.ogg"),
            SoundList("cycdh_ludsdstc_04.ogg"),
            SoundList("cycdh_ludsdstc_05.ogg"),
            SoundList("cycdh_ludsdstc_06.ogg"),
            SoundList("cycdh_ludsdstc_07.ogg"),
            SoundList("cycdh_ludsnrc_01.ogg"),
            SoundList("cycdh_ludsnrc_02.ogg"),
            SoundList("cycdh_ludsnrc_03.ogg"),
            SoundList("cycdh_ludsnrc_04.ogg"),
            SoundList("cycdh_ludsnrc_05.ogg"),
            SoundList("cycdh_ludsnroffc_01.ogg"),
            SoundList("cycdh_ludsnroffc_02.ogg"),
            SoundList("cycdh_ludsnroffc_03.ogg"),
            SoundList("cycdh_ludsnroffc_04.ogg"),
            SoundList("cycdh_ludsnroffc_05.ogg"),
            SoundList("cycdh_ludsnroffc_06.ogg"),
            SoundList("cycdh_ludsnroffc_07.ogg"),
            SoundList("cycdh_ludsnroffc_08.ogg")
        )
        mSoundList = arrayListOf(
            SoundList("pearlpiccolo_side_snroff_01.ogg"),
            SoundList("pearlpiccolo_side_snroff_02.ogg"),
            SoundList("pearlpiccolo_side_snroff_03.ogg"),
            SoundList("pearlpiccolo_side_snroff_04.ogg"),
            SoundList("pearlpiccolo_side_snroff_05.ogg"),
            SoundList("pearlpiccolo_side_snron_01.ogg"),
            SoundList("pearlpiccolo_side_snron_02.ogg"),
            SoundList("pearlpiccolo_side_snron_03.ogg"),
            SoundList("pearlpiccolo_side_snron_04.ogg"),
            SoundList("pearlpiccolo_side_snron_05.ogg"),
            SoundList("pearlpiccolo_side_snron_06.ogg"),
            SoundList("pearlpiccolo_side_snron_07.ogg"),
            SoundList("pearlpiccolo_side_snron_08.ogg"),
            SoundList("pearlpiccolo_side_snron_09.ogg")
        )
        aaSoundList = arrayListOf(
            SoundList("cycdh_sonflam_01.ogg"),
            SoundList("cycdh_sonflam_02.ogg"),
            SoundList("cycdh_sonflam_03.ogg"),
            SoundList("cycdh_sonrim_01.ogg"),
            SoundList("cycdh_sonrim_02.ogg"),
            SoundList("cycdh_sonrim_03.ogg"),
            SoundList("cycdh_sonrim_04.ogg"),
            SoundList("cycdh_sonrim_05.ogg"),
            SoundList("cycdh_sonrim_06.ogg"),
            SoundList("cycdh_sonrim_07.ogg"),
            SoundList("cycdh_sonrim_08.ogg"),
            SoundList("cycdh_sonrim_09.ogg"),
            SoundList("cycdh_sonrim_10.ogg"),
            SoundList("cycdh_sonsdst_01.ogg"),
            SoundList("cycdh_sonsdst_02.ogg"),
            SoundList("cycdh_sonsdst_03.ogg"),
            SoundList("cycdh_sonsdst_04.ogg"),
            SoundList("cycdh_sonsdst_05.ogg"),
            SoundList("cycdh_sonsdst_06.ogg"),
            SoundList("cycdh_sonsdst_07.ogg"),
            SoundList("cycdh_sonsnr_01.ogg"),
            SoundList("cycdh_sonsnr_02.ogg"),
            SoundList("cycdh_sonsnr_03.ogg"),
            SoundList("cycdh_sonsnr_04.ogg"),
            SoundList("cycdh_sonsnr_05.ogg"),
            SoundList("cycdh_sonsnr_06.ogg"),
            SoundList("cycdh_sonsnroff_01.ogg"),
            SoundList("cycdh_sonsnroff_02.ogg"),
            SoundList("cycdh_sonsnroff_03.ogg"),
            SoundList("cycdh_sonsnroff_04.ogg"),
            SoundList("cycdh_sonsnroff_05.ogg"),
            SoundList("cycdh_sonsnroff_06.ogg"),
            SoundList("cycdh_sonupsd_01.ogg"),
            SoundList("cycdh_sonupsd_02.ogg"),
            SoundList("cycdh_sonupsd_03.ogg"),
            SoundList("cycdh_sonupsd_04.ogg"),
            SoundList("cycdh_sonupsd_05.ogg"),
            SoundList("cycdh_sonupsd_06.ogg"),
            SoundList("cycdh_sonupsd_07.ogg"),
            SoundList("cycdh_sonupsd_08.ogg")
        )
        abSoundList = arrayListOf(
            SoundList("cycdh_tamflam_01.ogg"),
            SoundList("cycdh_tamflam_02.ogg"),
            SoundList("cycdh_tamflam_03.ogg"),
            SoundList("cycdh_tamrim_01.ogg"),
            SoundList("cycdh_tamrim_02.ogg"),
            SoundList("cycdh_tamrim_03.ogg"),
            SoundList("cycdh_tamrim_04.ogg"),
            SoundList("cycdh_tamrim_05.ogg"),
            SoundList("cycdh_tamrim_06.ogg"),
            SoundList("cycdh_tamrim_07.ogg"),
            SoundList("cycdh_tamrim_08.ogg"),
            SoundList("cycdh_tamrimtgt_01.ogg"),
            SoundList("cycdh_tamrimtgt_02.ogg"),
            SoundList("cycdh_tamrimtgt_03.ogg"),
            SoundList("cycdh_tamrimtgt_04.ogg"),
            SoundList("cycdh_tamrimtgt_05.ogg"),
            SoundList("cycdh_tamsdst_01.ogg"),
            SoundList("cycdh_tamsdst_02.ogg"),
            SoundList("cycdh_tamsdst_03.ogg"),
            SoundList("cycdh_tamsdst_04.ogg"),
            SoundList("cycdh_tamsdst_05.ogg"),
            SoundList("cycdh_tamsdst_06.ogg"),
            SoundList("cycdh_tamsdst_07.ogg"),
            SoundList("cycdh_tamsdst_08.ogg"),
            SoundList("cycdh_tamsnr_01.ogg"),
            SoundList("cycdh_tamsnr_02.ogg"),
            SoundList("cycdh_tamsnr_03.ogg"),
            SoundList("cycdh_tamsnr_04.ogg"),
            SoundList("cycdh_tamsnr_05.ogg"),
            SoundList("cycdh_tamsnr_06.ogg"),
            SoundList("cycdh_tamsnr_07.ogg"),
            SoundList("cycdh_tamsnr_08.ogg"),
            SoundList("cycdh_tamsnred_01.ogg"),
            SoundList("cycdh_tamsnred_02.ogg"),
            SoundList("cycdh_tamsnred_03.ogg"),
            SoundList("cycdh_tamsnred_04.ogg"),
            SoundList("cycdh_tamsnred_05.ogg"),
            SoundList("cycdh_tamsnred_06.ogg"),
            SoundList("cycdh_tamsnred_07.ogg"),
            SoundList("cycdh_tamsnred_08.ogg"),
            SoundList("cycdh_tamsnrtgt_01.ogg"),
            SoundList("cycdh_tamsnrtgt_02.ogg"),
            SoundList("cycdh_tamsnrtgt_03.ogg"),
            SoundList("cycdh_tamsnrtgt_04.ogg"),
            SoundList("cycdh_tamsnrtgt_05.ogg"),
            SoundList("cycdh_tamsnrtgt_06.ogg"),
            SoundList("cycdh_tamsnrtgt_07.ogg"),
            SoundList("cycdh_tamupsd_01.ogg"),
            SoundList("cycdh_tamupsd_02.ogg"),
            SoundList("cycdh_tamupsd_03.ogg"),
            SoundList("cycdh_tamupsd_04.ogg"),
            SoundList("cycdh_tamupsd_05.ogg"),
            SoundList("cycdh_tamupsd_06.ogg"),
            SoundList("cycdh_tamupsd_07.ogg"),
            SoundList("cycdh_tamupsd_08.ogg"),
            SoundList("cycdh_tamupsd_09.ogg")
        )
        acSoundList = arrayListOf(
            SoundList("cycdh_piccoloa_01.ogg"),
            SoundList("cycdh_piccoloa_02.ogg"),
            SoundList("cycdh_piccoloa_03.ogg"),
            SoundList("cycdh_piccoloa_04.ogg"),
            SoundList("cycdh_piccolob_01.ogg"),
            SoundList("cycdh_piccolob_02.ogg"),
            SoundList("cycdh_piccolob_03.ogg"),
            SoundList("cycdh_piccolob_04.ogg"),
            SoundList("cycdh_piccoloc_01.ogg"),
            SoundList("cycdh_piccoloc_02.ogg"),
            SoundList("cycdh_piccoloc_03.ogg"),
            SoundList("cycdh_piccoloc_04.ogg"),
            SoundList("cycdh_piccolod_01.ogg"),
            SoundList("cycdh_piccolod_02.ogg"),
            SoundList("cycdh_piccolod_03.ogg"),
            SoundList("cycdh_piccolod_04.ogg"),
            SoundList("cycdh_piccoloe_01.ogg"),
            SoundList("cycdh_piccoloe_02.ogg"),
            SoundList("cycdh_piccoloe_03.ogg"),
            SoundList("cycdh_piccoloe_04.ogg")
        )
        adSoundList = arrayListOf(
            SoundList("tom_1.ogg"),
            SoundList("tom_2.ogg"),
            SoundList("tom_3.ogg"),
            SoundList("tom_4.ogg"),
            SoundList("tom_5.ogg"),
            SoundList("tom_6.ogg"),
            SoundList("tom_7.ogg"),
            SoundList("tom_8.ogg"),
            SoundList("tom_9.ogg"),
            SoundList("tom_10.ogg"),
            SoundList("tom_11.ogg"),
            SoundList("tom_12.ogg"),
            SoundList("tom_13.ogg"),
            SoundList("tom_14.ogg"),
            SoundList("tom_15.ogg"),
            SoundList("tom_16.ogg"),
            SoundList("tom_17.ogg"),
            SoundList("tom_18.ogg"),
            SoundList("tom_19.ogg"),
            SoundList("tom_20.ogg"),
            SoundList("tom_21.ogg"),
            SoundList("tom_22.ogg"),
            SoundList("tom_23.ogg"),
            SoundList("tom_24.ogg"),
            SoundList("tom_25.ogg"),
            SoundList("tom_26.ogg"),
            SoundList("tom_27.ogg"),
            SoundList("tom_28.ogg"),
            SoundList("tom_29.ogg"),
            SoundList("tom_30.ogg"),
            SoundList("tom_31.ogg"),
            SoundList("tom_32.ogg"),
            SoundList("tom_33.ogg"),
            SoundList("tom_34.ogg"),
            SoundList("tom_35.ogg"),
            SoundList("tom_36.ogg"),
            SoundList("tom_37.ogg"),
            SoundList("tom_38.ogg"),
            SoundList("tom_39.ogg"),
            SoundList("tom_40.ogg"),
            SoundList("tom_41.ogg"),
            SoundList("tom_42.ogg"),
            SoundList("tom_43.ogg")
        )
        nSoundList = arrayListOf(
            SoundList("bass_85_01.ogg"),
            SoundList("bass_85_02.ogg"),
            SoundList("bass_85_03.ogg"),
            SoundList("bass_90_01.ogg"),
            SoundList("bass_90_02.ogg"),
            SoundList("bass_90_03.ogg"),
            SoundList("bass_90_04.ogg"),
            SoundList("bass_90_05.ogg"),
            SoundList("bass_95_01.ogg"),
            SoundList("bass_95_02.ogg"),
            SoundList("bass_95_03.ogg"),
            SoundList("bass_100_01.ogg"),
            SoundList("bass_100_02.ogg"),
            SoundList("bass_100_03.ogg"),
            SoundList("bass_100_04.ogg"),
            SoundList("bass_100_05.ogg"),
            SoundList("bass_110_01.ogg"),
            SoundList("bass_110_02.ogg"),
            SoundList("bass_110_03.ogg"),
            SoundList("bass_110_04.ogg"),
            SoundList("bass_110_05.ogg"),
            SoundList("bass_110_06.ogg"),
            SoundList("bass_110_07.ogg"),
            SoundList("bass_110_08.ogg"),
            SoundList("bass_120_01.ogg"),
            SoundList("bass_120_02.ogg"),
            SoundList("bass_120_03.ogg"),
            SoundList("bass_120_04.ogg"),
            SoundList("bass_120_05.ogg"),
            SoundList("bass_120_06.ogg"),
            SoundList("bass_120_07.ogg"),
            SoundList("bass_120_08.ogg"),
        )
        oSoundList = arrayListOf(
            SoundList("drums_85_01.ogg"),
            SoundList("drums_85_02.ogg"),
            SoundList("drums_90_01.ogg"),
            SoundList("drums_90_02.ogg"),
            SoundList("drums_90_03.ogg"),
            SoundList("drums_90_04.ogg"),
            SoundList("drums_90_05.ogg"),
            SoundList("drums_90_06.ogg"),
            SoundList("drums_90_07.ogg"),
            SoundList("drums_90_08.ogg"),
            SoundList("drums_90_09.ogg"),
            SoundList("drums_90_10.ogg"),
            SoundList("drums_90_11.ogg"),
            SoundList("drums_90_12.ogg"),
            SoundList("drums_90_13.ogg"),
            SoundList("drums_90_14.ogg"),
            SoundList("drums_90_15.ogg"),
            SoundList("drums_95_01.ogg"),
            SoundList("drums_95_02.ogg"),
            SoundList("drums_95_03.ogg"),
            SoundList("drums_95_04.ogg"),
            SoundList("drums_95_05.ogg"),
            SoundList("drums_95_06.ogg"),
            SoundList("drums_95_07.ogg"),
            SoundList("drums_95_08.ogg"),
            SoundList("drums_95_09.ogg"),
            SoundList("drums_95_10.ogg"),
            SoundList("drums_95_11.ogg"),
            SoundList("drums_95_12.ogg"),
            SoundList("drums_95_13.ogg"),
            SoundList("drums_95_14.ogg"),
            SoundList("drums_95_15.ogg"),
            SoundList("drums_95_16.ogg"),
            SoundList("drums_95_17.ogg"),
            SoundList("drums_95_18.ogg"),
            SoundList("drums_95_19.ogg"),
            SoundList("drums_100_01.ogg"),
            SoundList("drums_100_02.ogg"),
            SoundList("drums_100_03.ogg"),
            SoundList("drums_100_04.ogg"),
            SoundList("drums_100_05.ogg"),
            SoundList("drums_100_06.ogg"),
            SoundList("drums_100_07.ogg"),
            SoundList("drums_100_08.ogg"),
            SoundList("drums_110_01.ogg"),
            SoundList("drums_110_02.ogg"),
            SoundList("drums_110_03.ogg"),
            SoundList("drums_110_04.ogg"),
            SoundList("drums_110_05.ogg"),
            SoundList("drums_110_06.ogg"),
            SoundList("drums_110_07.ogg"),
            SoundList("drums_110_08.ogg"),
            SoundList("drums_110_09.ogg"),
            SoundList("drums_110_10.ogg"),
            SoundList("drums_110_11.ogg"),
            SoundList("drums_110_12.ogg"),
            SoundList("drums_110_13.ogg"),
            SoundList("drums_110_14.ogg"),
            SoundList("drums_110_15.ogg"),
            SoundList("drums_110_16.ogg"),
            SoundList("drums_110_17.ogg"),
            SoundList("drums_110_18.ogg"),
            SoundList("drums_110_19.ogg"),
            SoundList("drums_110_20.ogg"),
            SoundList("drums_110_21.ogg"),
            SoundList("drums_110_22.ogg"),
            SoundList("drums_110_23.ogg"),
            SoundList("drums_110_24.ogg"),
            SoundList("drums_110_25.ogg"),
            SoundList("drums_110_26.ogg"),
            SoundList("drums_120_01.ogg"),
            SoundList("drums_120_02.ogg"),
            SoundList("drums_120_03.ogg"),
            SoundList("drums_120_04.ogg"),
            SoundList("drums_120_05.ogg"),
            SoundList("drums_120_06.ogg"),
            SoundList("drums_120_07.ogg"),
            SoundList("drums_120_08.ogg"),
            SoundList("drums_120_09.ogg"),
            SoundList("drums_120_10.ogg"),
            SoundList("drums_120_11.ogg"),
            SoundList("drums_120_12.ogg"),
            SoundList("drums_120_13.ogg"),
            SoundList("drums_120_14.ogg"),
            SoundList("drums_120_15.ogg"),
            SoundList("drums_120_16.ogg"),
            SoundList("drums_120_17.ogg"),
            SoundList("drums_120_18.ogg"),
            SoundList("drums_120_19.ogg"),
            SoundList("drums_120_20.ogg"),
            SoundList("drums_120_21.ogg")
        )
        pSoundList = arrayListOf(
            SoundList("guitar_85_01.ogg"),
            SoundList("guitar_85_02.ogg"),
            SoundList("guitar_85_03.ogg"),
            SoundList("guitar_85_04.ogg"),
            SoundList("guitar_85_05.ogg"),
            SoundList("guitar_85_06.ogg"),
            SoundList("guitar_85_07.ogg"),
            SoundList("guitar_85_08.ogg"),
            SoundList("guitar_85_09.ogg"),
            SoundList("guitar_85_10.ogg"),
            SoundList("guitar_85_11.ogg"),
            SoundList("guitar_85_12.ogg"),
            SoundList("guitar_85_13.ogg"),
            SoundList("guitar_90_01.ogg"),
            SoundList("guitar_90_02.ogg"),
            SoundList("guitar_90_03.ogg"),
            SoundList("guitar_90_04.ogg"),
            SoundList("guitar_90_05.ogg"),
            SoundList("guitar_90_06.ogg"),
            SoundList("guitar_90_07.ogg"),
            SoundList("guitar_90_08.ogg"),
            SoundList("guitar_90_09.ogg"),
            SoundList("guitar_90_10.ogg"),
            SoundList("guitar_90_11.ogg"),
            SoundList("guitar_90_12.ogg"),
            SoundList("guitar_90_13.ogg"),
            SoundList("guitar_90_14.ogg"),
            SoundList("guitar_90_15.ogg"),
            SoundList("guitar_90_16.ogg"),
            SoundList("guitar_90_17.ogg"),
            SoundList("guitar_90_18.ogg"),
            SoundList("guitar_90_19.ogg"),
            SoundList("guitar_90_20.ogg"),
            SoundList("guitar_90_21.ogg"),
            SoundList("guitar_95_01.ogg"),
            SoundList("guitar_95_02.ogg"),
            SoundList("guitar_95_03.ogg"),
            SoundList("guitar_95_04.ogg"),
            SoundList("guitar_95_05.ogg"),
            SoundList("guitar_95_06.ogg"),
            SoundList("guitar_95_07.ogg"),
            SoundList("guitar_95_08.ogg"),
            SoundList("guitar_100_01.ogg"),
            SoundList("guitar_100_02.ogg"),
            SoundList("guitar_100_03.ogg"),
            SoundList("guitar_100_04.ogg"),
            SoundList("guitar_100_05.ogg"),
            SoundList("guitar_100_06.ogg"),
            SoundList("guitar_110_01.ogg"),
            SoundList("guitar_110_02.ogg"),
            SoundList("guitar_110_03.ogg"),
            SoundList("guitar_110_04.ogg"),
            SoundList("guitar_110_05.ogg"),
            SoundList("guitar_110_06.ogg"),
            SoundList("guitar_110_07.ogg"),
            SoundList("guitar_110_08.ogg"),
            SoundList("guitar_110_09.ogg"),
            SoundList("guitar_110_10.ogg"),
            SoundList("guitar_110_11.ogg"),
            SoundList("guitar_120_01.ogg"),
            SoundList("guitar_120_02.ogg"),
            SoundList("guitar_120_03.ogg"),
            SoundList("guitar_120_04.ogg"),
            SoundList("guitar_120_05.ogg"),
            SoundList("guitar_120_06.ogg"),
            SoundList("guitar_120_07.ogg"),
            SoundList("guitar_120_08.ogg"),
            SoundList("guitar_120_09.ogg"),
            SoundList("guitar_120_10.ogg"),
            SoundList("guitar_120_11.ogg"),
            SoundList("guitar_120_12.ogg"),
            SoundList("guitar_120_13.ogg"),
            SoundList("guitar_120_14.ogg"),
            SoundList("guitar_120_15.ogg"),
            SoundList("guitar_120_16.ogg"),
            SoundList("guitar_120_17.ogg"),
            SoundList("guitar_120_18.ogg"),
            SoundList("guitar_120_19.ogg"),
            SoundList("guitar_120_20.ogg"),
            SoundList("guitar_120_21.ogg")
        )
        qSoundList = arrayListOf(
            SoundList("keys_85_01.ogg"),
            SoundList("keys_85_02.ogg"),
            SoundList("keys_90_01.ogg"),
            SoundList("keys_90_02.ogg"),
            SoundList("keys_90_03.ogg"),
            SoundList("keys_90_04.ogg"),
            SoundList("keys_90_05.ogg"),
            SoundList("keys_90_06.ogg"),
            SoundList("keys_90_07.ogg"),
            SoundList("keys_90_08.ogg"),
            SoundList("keys_95_01.ogg"),
            SoundList("keys_95_02.ogg"),
            SoundList("keys_95_03.ogg"),
            SoundList("keys_100_01.ogg"),
            SoundList("keys_100_02.ogg"),
            SoundList("keys_100_03.ogg"),
            SoundList("keys_100_04.ogg"),
            SoundList("keys_100_05.ogg"),
            SoundList("keys_110_01.ogg"),
            SoundList("keys_110_02.ogg"),
            SoundList("keys_110_03.ogg"),
            SoundList("keys_110_04.ogg"),
            SoundList("keys_110_05.ogg"),
            SoundList("keys_110_06.ogg"),
            SoundList("keys_120_01.ogg"),
            SoundList("keys_120_02.ogg"),
            SoundList("keys_120_03.ogg"),
            SoundList("keys_120_04.ogg"),
            SoundList("keys_120_05.ogg"),
            SoundList("keys_120_06.ogg")
        )
        rSoundList = arrayListOf(
            SoundList("orch_85_01.ogg"),
            SoundList("orch_95.ogg"),
            SoundList("orch_100_01.ogg"),
            SoundList("orch_100_02.ogg"),
            SoundList("orch_100_03.ogg"),
            SoundList("orch_110_01.ogg"),
            SoundList("orch_110_02.ogg")
        )
        baSoundList = arrayListOf(
            SoundList("percussion_85_01.ogg"),
            SoundList("percussion_85_02.ogg"),
            SoundList("percussion_85_03.ogg"),
            SoundList("percussion_85_04.ogg"),
            SoundList("percussion_90_01.ogg"),
            SoundList("percussion_90_02.ogg"),
            SoundList("percussion_90_03.ogg"),
            SoundList("percussion_90_04.ogg"),
            SoundList("percussion_90_05.ogg"),
            SoundList("percussion_90_06.ogg"),
            SoundList("percussion_90_07.ogg"),
            SoundList("percussion_90_08.ogg"),
            SoundList("percussion_90_09.ogg"),
            SoundList("percussion_90_10.ogg"),
            SoundList("percussion_95_01.ogg"),
            SoundList("percussion_95_02.ogg"),
            SoundList("percussion_95_03.ogg"),
            SoundList("percussion_100_01.ogg"),
            SoundList("percussion_100_02.ogg"),
            SoundList("percussion_100_03.ogg"),
            SoundList("percussion_100_04.ogg"),
            SoundList("percussion_100_05.ogg"),
            SoundList("percussion_100_06.ogg"),
            SoundList("percussion_100_07.ogg"),
            SoundList("percussion_100_08.ogg"),
            SoundList("percussion_100_09.ogg"),
            SoundList("percussion_100_10.ogg"),
            SoundList("percussion_100_11.ogg"),
            SoundList("percussion_100_12.ogg"),
            SoundList("percussion_110_01.ogg"),
            SoundList("percussion_110_02.ogg"),
            SoundList("percussion_110_03.ogg"),
            SoundList("percussion_110_04.ogg"),
            SoundList("percussion_110_05.ogg"),
            SoundList("percussion_110_06.ogg"),
            SoundList("percussion_110_07.ogg"),
            SoundList("percussion_110_08.ogg"),
            SoundList("percussion_110_09.ogg"),
            SoundList("percussion_110_10.ogg"),
            SoundList("percussion_110_11.ogg"),
            SoundList("percussion_110_12.ogg"),
            SoundList("percussion_110_13.ogg"),
            SoundList("percussion_110_14.ogg"),
            SoundList("percussion_110_15.ogg"),
            SoundList("percussion_120_01.ogg"),
            SoundList("percussion_120_02.ogg"),
            SoundList("percussion_120_03.ogg"),
            SoundList("percussion_120_04.ogg"),
            SoundList("percussion_120_05.ogg"),
            SoundList("percussion_120_06.ogg"),
            SoundList("percussion_120_07.ogg"),
            SoundList("percussion_120_08.ogg"),
            SoundList("percussion_120_09.ogg"),
            SoundList("percussion_120_10.ogg"),
            SoundList("percussion_120_11.ogg"),
            SoundList("percussion_120_12.ogg"),
            SoundList("percussion_120_13.ogg")
        )
        bbSoundList = arrayListOf(
            SoundList("synth_90_01.ogg"),
            SoundList("synth_90_02.ogg"),
            SoundList("synth_90_03.ogg"),
            SoundList("synth_90_04.ogg"),
            SoundList("synth_90_05.ogg"),
            SoundList("synth_90_06.ogg"),
            SoundList("synth_90_07.ogg"),
            SoundList("synth_95_01.ogg"),
            SoundList("synth_95_02.ogg"),
            SoundList("synth_100_01.ogg"),
            SoundList("synth_100_02.ogg"),
            SoundList("synth_100_03.ogg"),
            SoundList("synth_100_04.ogg"),
            SoundList("synth_100_05.ogg"),
            SoundList("synth_110_01.ogg"),
            SoundList("synth_110_02.ogg"),
            SoundList("synth_110_03.ogg"),
            SoundList("synth_110_04.ogg"),
            SoundList("synth_110_05.ogg"),
            SoundList("synth_110_06.ogg"),
            SoundList("synth_110_07.ogg"),
            SoundList("synth_120_01.ogg"),
            SoundList("synth_120_02.ogg"),
            SoundList("synth_120_03.ogg"),
            SoundList("synth_120_04.ogg")
        )
        sSoundList = arrayListOf()
        tSoundList = arrayListOf(
            SoundList("pearlpiccolo_side_snroff_01.ogg"),
            SoundList("pearlpiccolo_side_snron_01.ogg"),
            SoundList("acoustic_hat_01.ogg"),
            SoundList("khats_clsd_01.ogg"),
            SoundList("closed_hat_03.ogg"),
            SoundList("tom_2.ogg"),
            SoundList("kick_04.ogg"),
        )

        a0CustomAdapter = CustomAdapter(this, a0SoundList, this)
        aCustomAdapter = CustomAdapter(this, aSoundList, this)
        bCustomAdapter = CustomAdapter(this, bSoundList, this)
        cCustomAdapter = CustomAdapter(this, cSoundList, this)
        dCustomAdapter = CustomAdapter(this, dSoundList, this)
        eCustomAdapter = CustomAdapter(this, eSoundList, this)
        fCustomAdapter = CustomAdapter(this, fSoundList, this)
        gCustomAdapter = CustomAdapter(this, gSoundList, this)
        hCustomAdapter = CustomAdapter(this, hSoundList, this)
        iCustomAdapter = CustomAdapter(this, iSoundList, this)
        jCustomAdapter = CustomAdapter(this, jSoundList, this)
        kCustomAdapter = CustomAdapter(this, kSoundList, this)
        lCustomAdapter = CustomAdapter(this, lSoundList, this)
        mCustomAdapter = CustomAdapter(this, mSoundList, this)
        aaCustomAdapter = CustomAdapter(this, aaSoundList, this)
        abCustomAdapter = CustomAdapter(this, abSoundList, this)
        acCustomAdapter = CustomAdapter(this, acSoundList, this)
        adCustomAdapter = CustomAdapter(this, adSoundList, this)
        nCustomAdapter = CustomAdapter(this, nSoundList, this)
        oCustomAdapter = CustomAdapter(this, oSoundList, this)
        pCustomAdapter = CustomAdapter(this, pSoundList, this)
        qCustomAdapter = CustomAdapter(this, qSoundList, this)
        rCustomAdapter = CustomAdapter(this, rSoundList, this)
        baCustomAdapter = CustomAdapter(this, baSoundList, this)
        bbCustomAdapter = CustomAdapter(this, bbSoundList, this)
        sCustomAdapter = CustomAdapter(this, sSoundList, this)
        tCustomAdapter = CustomAdapter(this, tSoundList, this)

        soundListView.adapter = aCustomAdapter

        mp = MediaPlayer()

        supportActionBar?.title = actionTitle


            val audioUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
            val cursor = contentResolver.query(audioUri, null, null, null, null)
            cursor!!.moveToFirst()
            val path: Array<String?> = arrayOfNulls(cursor.count)
            for (i in path.indices) {
                path[i] = cursor.getString(cursor.getColumnIndex("_data"))
                sSoundList.add(SoundList(path[i].toString()))
                cursor.moveToNext()
            }

            cursor.close()


        val meSpinner = findViewById<Spinner>(R.id.menu_spinner)

        val adapter3 = ArrayAdapter.createFromResource(this, R.array.spinnerItems, android.R.layout.simple_spinner_item)

        adapter3.setDropDownViewResource(R.layout.custom_spinner_dropdown)



        meSpinner.adapter = adapter3


        meSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long,
            ) {
                if (!meSpinner.isFocusable) {
                    meSpinner.isFocusable = true
                    return
                }

                when(position){
                    0 -> {
                        buttonB = 2
                        soundListView.adapter = a0CustomAdapter
                        a0CustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    1 -> {
                        buttonB = 2
                        soundListView.adapter = aCustomAdapter
                        aCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    2 -> {
                        buttonB = 2
                        soundListView.adapter = bCustomAdapter
                        bCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    3 -> {
                        buttonB = 2
                        soundListView.adapter = cCustomAdapter
                        cCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    4 -> {
                        buttonB = 2
                        soundListView.adapter = dCustomAdapter
                        dCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    5 -> {
                        buttonB = 2
                        soundListView.adapter = eCustomAdapter
                        eCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    6 -> {
                        buttonB = 2
                        soundListView.adapter = fCustomAdapter
                        fCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    7 -> {
                        buttonB = 2
                        soundListView.adapter = gCustomAdapter
                        gCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    8 -> {
                        buttonB = 2
                        soundListView.adapter = hCustomAdapter
                        hCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    9 -> {
                        buttonB = 2
                        soundListView.adapter = iCustomAdapter
                        iCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    10 -> {
                        buttonB = 2
                        soundListView.adapter = jCustomAdapter
                        jCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    11 -> {
                        buttonB = 2
                        soundListView.adapter = kCustomAdapter
                        kCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    12 -> {
                        buttonB = 2
                        soundListView.adapter = lCustomAdapter
                        lCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    13 -> {
                        buttonB = 2
                        soundListView.adapter = mCustomAdapter
                        mCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    14 -> {
                        buttonB = 2
                        soundListView.adapter = aaCustomAdapter
                        aaCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    15 -> {
                        buttonB = 2
                        soundListView.adapter = abCustomAdapter
                        abCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    16 -> {
                        buttonB = 2
                        soundListView.adapter = acCustomAdapter
                        acCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    17 -> {
                        buttonB = 2
                        soundListView.adapter = adCustomAdapter
                        adCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    18 -> {
                        buttonB = 1
                        soundListView.adapter = sCustomAdapter
                        sCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    19 -> {
                        selectEX()
                        buttonB = 1
                        soundListView.adapter = tCustomAdapter
                        tCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        meSpinner.isFocusable = false


        val audioAttributes = AudioAttributes.Builder()

                .setUsage(AudioAttributes.USAGE_GAME)

                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()

        soundPool = SoundPool.Builder()

                .setAudioAttributes(audioAttributes)

                .setMaxStreams(20)
                .build()

        when (orientation) {

            Configuration.ORIENTATION_PORTRAIT -> {

                sound1 = soundPool.load(assets.openFd("cymbal_01.ogg"), 1)

                sound2 = soundPool.load(assets.openFd("soundless.ogg"), 1)

                sound3 = soundPool.load(assets.openFd("cymbal_02.ogg"), 1)

                sound4 = soundPool.load(assets.openFd("open_hat_01.ogg"), 1)

                sound5 = soundPool.load(assets.openFd("soundless.ogg"), 1)

                sound6 = soundPool.load(assets.openFd("cymbal_03.ogg"), 1)

                sound7 = soundPool.load(assets.openFd("closed_hat_01.ogg"), 1)

                sound8 = soundPool.load(assets.openFd("tom_02.ogg"), 1)

                sound9 = soundPool.load(assets.openFd("tom_01.ogg"), 1)

                sound10 = soundPool.load(assets.openFd("snare_01.ogg"), 1)

                sound11 = soundPool.load(assets.openFd("kick_01.ogg"), 1)

                sound12 = soundPool.load(assets.openFd("tom_03.ogg"), 1)

                sound13 = soundPool.load(assets.openFd("soundless.ogg"), 1)

                sound14 = soundPool.load(assets.openFd("soundless.ogg"), 1)

                sound15 = soundPool.load(assets.openFd("soundless.ogg"), 1)

            }

            Configuration.ORIENTATION_LANDSCAPE -> {

                sound1 = soundPool.load(assets.openFd("cymbal_01.ogg"), 1)

                sound2 = soundPool.load(assets.openFd("open_hat_01.ogg"), 1)

                sound3 = soundPool.load(assets.openFd("closed_hat_01.ogg"), 1)

                sound4 = soundPool.load(assets.openFd("soundless.ogg"), 1)

                sound5 = soundPool.load(assets.openFd("tom_02.ogg"), 1)

                sound6 = soundPool.load(assets.openFd("snare_01.ogg"), 1)

                sound7 = soundPool.load(assets.openFd("soundless.ogg"), 1)

                sound8 = soundPool.load(assets.openFd("soundless.ogg"), 1)

                sound9 = soundPool.load(assets.openFd("kick_01.ogg"), 1)

                sound10 = soundPool.load(assets.openFd("soundless.ogg"), 1)

                sound11 = soundPool.load(assets.openFd("tom_01.ogg"), 1)

                sound12 = soundPool.load(assets.openFd("tom_03.ogg"), 1)

                sound13 = soundPool.load(assets.openFd("cymbal_02.ogg"), 1)

                sound14 = soundPool.load(assets.openFd("cymbal_03.ogg"), 1)

                sound15 = soundPool.load(assets.openFd("soundless.ogg"), 1)

            }
        }

        lmp = LoopMediaPlayer.create(this, Uri.parse("android.resource://" + packageName + "/raw/" + R.raw.guitar_85_01))
        lmp.stop()


        binding.imageView.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound1, soundPoolVolume, soundPoolVolume, 1, 0, soundPoolTempo)
                }
            }
                false
        }

        binding.imageView2.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound2, soundPoolVolume2, soundPoolVolume2, 1, 0, soundPoolTempo2)
                }
            }
                false
        }

        binding.imageView3.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound3, soundPoolVolume3, soundPoolVolume3, 1, 0, soundPoolTempo3)
                }
            }
                false
        }

        binding.imageView4.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound4, soundPoolVolume4, soundPoolVolume4, 1, 0, soundPoolTempo4)
                }
            }
                false
        }

        binding.imageView5.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound5, soundPoolVolume5, soundPoolVolume5, 1, 0, soundPoolTempo5)
                }
            }
                false
        }

        binding.imageView6.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound6, soundPoolVolume6, soundPoolVolume6, 1, 0, soundPoolTempo6)
                }
            }
                false
        }

        binding.imageView7.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound7, soundPoolVolume7, soundPoolVolume7, 1, 0, soundPoolTempo7)
                }
            }
                false
        }

        binding.imageView8.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound8, soundPoolVolume8, soundPoolVolume8, 1, 0, soundPoolTempo8)
                }
            }
                false
        }

        binding.imageView9.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound9, soundPoolVolume9, soundPoolVolume9, 1, 0, soundPoolTempo9)
                }
            }
                false

        }

        binding.imageView10.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound10, soundPoolVolume10, soundPoolVolume10, 1, 0, soundPoolTempo10)
                }
            }
                false
        }

        binding.imageView11.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound11, soundPoolVolume11, soundPoolVolume11, 1, 0, soundPoolTempo11)
                }
            }
                false
        }

        binding.imageView12.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound12, soundPoolVolume12, soundPoolVolume12, 1, 0, soundPoolTempo12)
                }
            }
                false
        }

        binding.imageView13.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound13, soundPoolVolume13, soundPoolVolume13, 1, 0, soundPoolTempo13)
                }
            }
                false
        }

        binding.imageView14.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound14, soundPoolVolume14, soundPoolVolume14, 1, 0, soundPoolTempo14)
                }
            }
                false
        }

        binding.imageView15.setOnTouchListener { _, event ->
            when {
                gridView.isVisible -> {
                    gridView.visibility = View.INVISIBLE
                }
                gridView2.isVisible -> {
                    gridView2.visibility = View.INVISIBLE
                }
                soundListView.isVisible -> {
                    soundListView.visibility = View.INVISIBLE
                }
                event.action == MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound15, soundPoolVolume15, soundPoolVolume15, 1, 0, soundPoolTempo15)
                }
            }
                false
        }


        binding.imageView.setOnClickListener {
            if (paste == 1) {
                buttonA = 1
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView2.setOnClickListener {
            if (paste == 1) {
                buttonA = 2
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView3.setOnClickListener {
            if (paste == 1) {
                buttonA = 3
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView4.setOnClickListener {
            if (paste == 1) {
                buttonA = 4
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView5.setOnClickListener {
            if (paste == 1) {
                buttonA = 5
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView6.setOnClickListener {
            if (paste == 1) {
                buttonA = 6
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView7.setOnClickListener {
            if (paste == 1) {
                buttonA = 7
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView8.setOnClickListener {
            if (paste == 1) {
                buttonA = 8
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView9.setOnClickListener {
            if (paste == 1) {
                buttonA = 9
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView10.setOnClickListener {
            if (paste == 1) {
                buttonA = 10
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView11.setOnClickListener {
            if (paste == 1) {
                buttonA = 11
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView12.setOnClickListener {
            if (paste == 1) {
                buttonA = 12
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView13.setOnClickListener {
            if (paste == 1) {
                buttonA = 13
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView14.setOnClickListener {
            if (paste == 1) {
                buttonA = 14
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }
        binding.imageView15.setOnClickListener {
            if (paste == 1) {
                buttonA = 15
                meSpinner.avoidDropdownFocus()
                meSpinner.performClick()
            }
        }

        findViewById<ImageButton>(R.id.volume_minus0).setOnClickListener {
            lmp.volumeMinus()
            if (count > 0.1f) {
                count -= 0.1f
                count = "%.1f".format(count).toFloat()
                findViewById<Button>(R.id.loop).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText0).text = count.toString().replace("f", "") + " " + actionTitle + " " + bpm.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText0).text = bpm.toString().replace("f", "") + " " + actionTitle + " " + count.toString().replace("f", "").uppercase()
                }
            }
        }
        findViewById<ImageButton>(R.id.volume_plus0).setOnClickListener {
            lmp.volumePlus()
            if (count < 1.0f) {
                count += 0.1f
                count = "%.1f".format(count).toFloat()
                findViewById<Button>(R.id.loop).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText0).text = count.toString().replace("f", "") + " " + actionTitle + " " + bpm.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText0).text = bpm.toString().replace("f", "") + " " + actionTitle + " " + count.toString().replace("f", "").uppercase()
                }
            }
        }
        findViewById<ImageButton>(R.id.tempo_minus0).setOnClickListener {
            lmp.speedDown()
            if (bpm > 0.1f) {
                bpm -= 0.1f
                bpm = "%.1f".format(bpm).toFloat()
                findViewById<Button>(R.id.loop).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText0).text = count.toString().replace("f", "") + " " + actionTitle + " " + bpm.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText0).text = bpm.toString().replace("f", "") + " " + actionTitle + " " + count.toString().replace("f", "").uppercase()
                }
                menuSwitch = false
                invalidateOptionsMenu()
                switch1 = 1
            }
        }
        findViewById<ImageButton>(R.id.tempo_plus0).setOnClickListener {
            lmp.speedUp()
            if (bpm < 6.0f) {
                bpm += 0.1f
                bpm = "%.1f".format(bpm).toFloat()
                findViewById<Button>(R.id.loop).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText0).text = count.toString().replace("f", "") + " " + actionTitle + " " + bpm.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText0).text = bpm.toString().replace("f", "") + " " + actionTitle + " " + count.toString().replace("f", "").uppercase()
                }
                menuSwitch = false
                invalidateOptionsMenu()
                switch1 = 1
            }
        }

        findViewById<Button>(R.id.loop).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (switch1 == 1) {
                        lmp.stop()
                        soundPool.autoPause()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1 = 2
                    } else {
                        lmp.start()
                        menuSwitch = false
                        invalidateOptionsMenu()
                        switch1 = 1
                    }
                }
            }
            false
        }
        findViewById<Button>(R.id.pad1).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound1, soundPoolVolume, soundPoolVolume, 1, 0, soundPoolTempo)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad2).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound2, soundPoolVolume2, soundPoolVolume2, 1, 0, soundPoolTempo2)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad3).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound3, soundPoolVolume3, soundPoolVolume3, 1, 0, soundPoolTempo3)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad4).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound4, soundPoolVolume4, soundPoolVolume4, 1, 0, soundPoolTempo4)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad5).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound5, soundPoolVolume5, soundPoolVolume5, 1, 0, soundPoolTempo5)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad6).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound6, soundPoolVolume6, soundPoolVolume6, 1, 0, soundPoolTempo6)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad7).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound7, soundPoolVolume7, soundPoolVolume7, 1, 0, soundPoolTempo7)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad8).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound8, soundPoolVolume8, soundPoolVolume8, 1, 0, soundPoolTempo8)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad9).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound9, soundPoolVolume9, soundPoolVolume9, 1, 0, soundPoolTempo9)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad10).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound10, soundPoolVolume10, soundPoolVolume10, 1, 0, soundPoolTempo10)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad11).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound11, soundPoolVolume11, soundPoolVolume11, 1, 0, soundPoolTempo11)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad12).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound12, soundPoolVolume12, soundPoolVolume12, 1, 0, soundPoolTempo12)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad13).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound13, soundPoolVolume13, soundPoolVolume13, 1, 0, soundPoolTempo13)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad14).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound14, soundPoolVolume14, soundPoolVolume14, 1, 0, soundPoolTempo14)
                }
            }
            false
        }
        findViewById<Button>(R.id.pad15).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool.play(sound15, soundPoolVolume15, soundPoolVolume15, 1, 0, soundPoolTempo15)
                }
            }
            false
        }
        findViewById<ImageButton>(R.id.volume_minus1).setOnClickListener {
            if (soundPoolVolume > 0.1f) {
                soundPoolVolume -= 0.1f
                soundPoolVolume = "%.1f".format(soundPoolVolume).toFloat()
                findViewById<TextView>(R.id.padText1).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolVolume.toString().replace("f", "") + " " + padText1 + " " + soundPoolTempo.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolTempo.toString().replace("f", "") + " " + padText1 + " " + soundPoolVolume.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound1, soundPoolVolume, soundPoolVolume, 1, 0, soundPoolTempo)
        }
        findViewById<ImageButton>(R.id.volume_plus1).setOnClickListener {
            if (soundPoolVolume < 1.0f) {
                soundPoolVolume += 0.1f
                soundPoolVolume = "%.1f".format(soundPoolVolume).toFloat()
                findViewById<TextView>(R.id.padText1).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolVolume.toString().replace("f", "") + " " + padText1 + " " + soundPoolTempo.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolTempo.toString().replace("f", "") + " " + padText1 + " " + soundPoolVolume.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound1, soundPoolVolume, soundPoolVolume, 1, 0, soundPoolTempo)
        }
        findViewById<ImageButton>(R.id.tempo_minus1).setOnClickListener {
            if (soundPoolTempo > 0.2f) {
                soundPoolTempo -= 0.1f
                soundPoolTempo = "%.1f".format(soundPoolTempo).toFloat()
                findViewById<TextView>(R.id.padText1).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolVolume.toString().replace("f", "") + " " + padText1 + " " + soundPoolTempo.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolTempo.toString().replace("f", "") + " " + padText1 + " " + soundPoolVolume.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo == 0.2f) {
                soundPoolTempo = 0.125f
                findViewById<TextView>(R.id.padText1).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolVolume.toString().replace("f", "") + " " + padText1 + " " + soundPoolTempo.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolTempo.toString().replace("f", "") + " " + padText1 + " " + soundPoolVolume.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound1, soundPoolVolume, soundPoolVolume, 1, 0, soundPoolTempo)
        }
        findViewById<ImageButton>(R.id.tempo_plus1).setOnClickListener {
            if (soundPoolTempo == 0.125f) {
                soundPoolTempo = 0.2f
                findViewById<TextView>(R.id.padText1).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolVolume.toString().replace("f", "") + " " + padText1 + " " + soundPoolTempo.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolTempo.toString().replace("f", "") + " " + padText1 + " " + soundPoolVolume.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo < 8.0f) {
                soundPoolTempo += 0.1f
                soundPoolTempo = "%.1f".format(soundPoolTempo).toFloat()
                findViewById<TextView>(R.id.padText1).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolVolume.toString().replace("f", "") + " " + padText1 + " " + soundPoolTempo.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText1).text = soundPoolTempo.toString().replace("f", "") + " " + padText1 + " " + soundPoolVolume.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound1, soundPoolVolume, soundPoolVolume, 1, 0, soundPoolTempo)
        }
        findViewById<ImageButton>(R.id.volume_minus2).setOnClickListener {
            if (soundPoolVolume2 > 0.1f) {
                soundPoolVolume2 -= 0.1f
                soundPoolVolume2 = "%.1f".format(soundPoolVolume2).toFloat()
                findViewById<TextView>(R.id.padText2).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolVolume2.toString().replace("f", "") + " " + padText2 + " " + soundPoolTempo2.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolTempo2.toString().replace("f", "") + " " + padText2 + " " + soundPoolVolume2.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound2, soundPoolVolume2, soundPoolVolume2, 1, 0, soundPoolTempo2)
        }
        findViewById<ImageButton>(R.id.volume_plus2).setOnClickListener {
            if (soundPoolVolume2 < 1.0f) {
                soundPoolVolume2 += 0.1f
                soundPoolVolume2 = "%.1f".format(soundPoolVolume2).toFloat()
                findViewById<TextView>(R.id.padText2).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolVolume2.toString().replace("f", "") + " " + padText2 + " " + soundPoolTempo2.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolTempo2.toString().replace("f", "") + " " + padText2 + " " + soundPoolVolume2.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound2, soundPoolVolume2, soundPoolVolume2, 1, 0, soundPoolTempo2)
        }
        findViewById<ImageButton>(R.id.tempo_minus2).setOnClickListener {
            if (soundPoolTempo2 > 0.2f) {
                soundPoolTempo2 -= 0.1f
                soundPoolTempo2 = "%.1f".format(soundPoolTempo2).toFloat()
                findViewById<TextView>(R.id.padText2).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolVolume2.toString().replace("f", "") + " " + padText2 + " " + soundPoolTempo2.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolTempo2.toString().replace("f", "") + " " + padText2 + " " + soundPoolVolume2.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo2 == 0.2f) {
                soundPoolTempo2 = 0.125f
                findViewById<TextView>(R.id.padText2).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolVolume2.toString().replace("f", "") + " " + padText2 + " " + soundPoolTempo2.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolTempo2.toString().replace("f", "") + " " + padText2 + " " + soundPoolVolume2.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound2, soundPoolVolume2, soundPoolVolume2, 1, 0, soundPoolTempo2)
        }
        findViewById<ImageButton>(R.id.tempo_plus2).setOnClickListener {
            if (soundPoolTempo2 == 0.125f) {
                soundPoolTempo2 = 0.2f
                findViewById<TextView>(R.id.padText2).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolVolume2.toString().replace("f", "") + " " + padText2 + " " + soundPoolTempo2.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolTempo2.toString().replace("f", "") + " " + padText2 + " " + soundPoolVolume2.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo2 < 8.0f) {
                soundPoolTempo2 += 0.1f
                soundPoolTempo2 = "%.1f".format(soundPoolTempo2).toFloat()
                findViewById<TextView>(R.id.padText2).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolVolume2.toString().replace("f", "") + " " + padText2 + " " + soundPoolTempo2.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText2).text = soundPoolTempo2.toString().replace("f", "") + " " + padText2 + " " + soundPoolVolume2.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound2, soundPoolVolume2, soundPoolVolume2, 1, 0, soundPoolTempo2)
        }
        findViewById<ImageButton>(R.id.volume_minus3).setOnClickListener {
            if (soundPoolVolume3 > 0.1f) {
                soundPoolVolume3 -= 0.1f
                soundPoolVolume3 = "%.1f".format(soundPoolVolume3).toFloat()
                findViewById<TextView>(R.id.padText3).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolVolume3.toString().replace("f", "") + " " + padText3 + " " + soundPoolTempo3.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolTempo3.toString().replace("f", "") + " " + padText3 + " " + soundPoolVolume3.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound3, soundPoolVolume3, soundPoolVolume3, 1, 0, soundPoolTempo3)
        }
        findViewById<ImageButton>(R.id.volume_plus3).setOnClickListener {
            if (soundPoolVolume3 < 1.0f) {
                soundPoolVolume3 += 0.1f
                soundPoolVolume3 = "%.1f".format(soundPoolVolume3).toFloat()
                findViewById<TextView>(R.id.padText3).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolVolume3.toString().replace("f", "") + " " + padText3 + " " + soundPoolTempo3.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolTempo3.toString().replace("f", "") + " " + padText3 + " " + soundPoolVolume3.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound3, soundPoolVolume3, soundPoolVolume3, 1, 0, soundPoolTempo3)
        }
        findViewById<ImageButton>(R.id.tempo_minus3).setOnClickListener {
            if (soundPoolTempo3 > 0.2f) {
                soundPoolTempo3 -= 0.1f
                soundPoolTempo3 = "%.1f".format(soundPoolTempo3).toFloat()
                findViewById<TextView>(R.id.padText3).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolVolume3.toString().replace("f", "") + " " + padText3 + " " + soundPoolTempo3.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolTempo3.toString().replace("f", "") + " " + padText3 + " " + soundPoolVolume3.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo3 == 0.2f) {
                soundPoolTempo3 = 0.125f
                findViewById<TextView>(R.id.padText3).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolVolume3.toString().replace("f", "") + " " + padText3 + " " + soundPoolTempo3.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolTempo3.toString().replace("f", "") + " " + padText3 + " " + soundPoolVolume3.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound3, soundPoolVolume3, soundPoolVolume3, 1, 0, soundPoolTempo3)
        }
        findViewById<ImageButton>(R.id.tempo_plus3).setOnClickListener {
            if (soundPoolTempo3 == 0.125f) {
                soundPoolTempo3 = 0.2f
                findViewById<TextView>(R.id.padText3).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolVolume3.toString().replace("f", "") + " " + padText3 + " " + soundPoolTempo3.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolTempo3.toString().replace("f", "") + " " + padText3 + " " + soundPoolVolume3.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo3 < 8.0f) {
                soundPoolTempo3 += 0.1f
                soundPoolTempo3 = "%.1f".format(soundPoolTempo3).toFloat()
                findViewById<TextView>(R.id.padText3).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolVolume3.toString().replace("f", "") + " " + padText3 + " " + soundPoolTempo3.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText3).text = soundPoolTempo3.toString().replace("f", "") + " " + padText3 + " " + soundPoolVolume3.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound3, soundPoolVolume3, soundPoolVolume3, 1, 0, soundPoolTempo3)
        }
        findViewById<ImageButton>(R.id.volume_minus4).setOnClickListener {
            if (soundPoolVolume4 > 0.1f) {
                soundPoolVolume4 -= 0.1f
                soundPoolVolume4 = "%.1f".format(soundPoolVolume4).toFloat()
                findViewById<TextView>(R.id.padText4).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolVolume4.toString().replace("f", "") + " " + padText4 + " " + soundPoolTempo4.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolTempo4.toString().replace("f", "") + " " + padText4 + " " + soundPoolVolume4.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound4, soundPoolVolume4, soundPoolVolume4, 1, 0, soundPoolTempo4)
        }
        findViewById<ImageButton>(R.id.volume_plus4).setOnClickListener {
            if (soundPoolVolume4 < 1.0f) {
                soundPoolVolume4 += 0.1f
                soundPoolVolume4 = "%.1f".format(soundPoolVolume4).toFloat()
                findViewById<TextView>(R.id.padText4).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolVolume4.toString().replace("f", "") + " " + padText4 + " " + soundPoolTempo4.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolTempo4.toString().replace("f", "") + " " + padText4 + " " + soundPoolVolume4.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound4, soundPoolVolume4, soundPoolVolume4, 1, 0, soundPoolTempo4)
        }
        findViewById<ImageButton>(R.id.tempo_minus4).setOnClickListener {
            if (soundPoolTempo4 > 0.2f) {
                soundPoolTempo4 -= 0.1f
                soundPoolTempo4 = "%.1f".format(soundPoolTempo4).toFloat()
                findViewById<TextView>(R.id.padText4).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolVolume4.toString().replace("f", "") + " " + padText4 + " " + soundPoolTempo4.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolTempo4.toString().replace("f", "") + " " + padText4 + " " + soundPoolVolume4.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo4 == 0.2f) {
                soundPoolTempo4 = 0.125f
                findViewById<TextView>(R.id.padText4).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolVolume4.toString().replace("f", "") + " " + padText4 + " " + soundPoolTempo4.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolTempo4.toString().replace("f", "") + " " + padText4 + " " + soundPoolVolume4.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound4, soundPoolVolume4, soundPoolVolume4, 1, 0, soundPoolTempo4)
        }
        findViewById<ImageButton>(R.id.tempo_plus4).setOnClickListener {
            if (soundPoolTempo4 == 0.125f) {
                soundPoolTempo4 = 0.2f
                findViewById<TextView>(R.id.padText4).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolVolume4.toString().replace("f", "") + " " + padText4 + " " + soundPoolTempo4.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolTempo4.toString().replace("f", "") + " " + padText4 + " " + soundPoolVolume4.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo4 < 8.0f) {
                soundPoolTempo4 += 0.1f
                soundPoolTempo4 = "%.1f".format(soundPoolTempo4).toFloat()
                findViewById<TextView>(R.id.padText4).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolVolume4.toString().replace("f", "") + " " + padText4 + " " + soundPoolTempo4.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText4).text = soundPoolTempo4.toString().replace("f", "") + " " + padText4 + " " + soundPoolVolume4.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound4, soundPoolVolume4, soundPoolVolume4, 1, 0, soundPoolTempo4)
        }
        findViewById<ImageButton>(R.id.volume_minus5).setOnClickListener {
            if (soundPoolVolume5 > 0.1f) {
                soundPoolVolume5 -= 0.1f
                soundPoolVolume5 = "%.1f".format(soundPoolVolume5).toFloat()
                findViewById<TextView>(R.id.padText5).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolVolume5.toString().replace("f", "") + " " + padText5 + " " + soundPoolTempo5.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolTempo5.toString().replace("f", "") + " " + padText5 + " " + soundPoolVolume5.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound5, soundPoolVolume5, soundPoolVolume5, 1, 0, soundPoolTempo5)
        }
        findViewById<ImageButton>(R.id.volume_plus5).setOnClickListener {
            if (soundPoolVolume5 < 1.0f) {
                soundPoolVolume5 += 0.1f
                soundPoolVolume5 = "%.1f".format(soundPoolVolume5).toFloat()
                findViewById<TextView>(R.id.padText5).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolVolume5.toString().replace("f", "") + " " + padText5 + " " + soundPoolTempo5.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolTempo5.toString().replace("f", "") + " " + padText5 + " " + soundPoolVolume5.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound5, soundPoolVolume5, soundPoolVolume5, 1, 0, soundPoolTempo5)
        }
        findViewById<ImageButton>(R.id.tempo_minus5).setOnClickListener {
            if (soundPoolTempo5 > 0.2f) {
                soundPoolTempo5 -= 0.1f
                soundPoolTempo5 = "%.1f".format(soundPoolTempo5).toFloat()
                findViewById<TextView>(R.id.padText5).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolVolume5.toString().replace("f", "") + " " + padText5 + " " + soundPoolTempo5.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolTempo5.toString().replace("f", "") + " " + padText5 + " " + soundPoolVolume5.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo5 == 0.2f) {
                soundPoolTempo5 = 0.125f
                findViewById<TextView>(R.id.padText5).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolVolume5.toString().replace("f", "") + " " + padText5 + " " + soundPoolTempo5.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolTempo5.toString().replace("f", "") + " " + padText5 + " " + soundPoolVolume5.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound5, soundPoolVolume5, soundPoolVolume5, 1, 0, soundPoolTempo5)
        }
        findViewById<ImageButton>(R.id.tempo_plus5).setOnClickListener {
            if (soundPoolTempo5 == 0.125f) {
                soundPoolTempo5 = 0.2f
                findViewById<TextView>(R.id.padText5).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolVolume5.toString().replace("f", "") + " " + padText5 + " " + soundPoolTempo5.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolTempo5.toString().replace("f", "") + " " + padText5 + " " + soundPoolVolume5.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo5 < 8.0f) {
                soundPoolTempo5 += 0.1f
                soundPoolTempo5 = "%.1f".format(soundPoolTempo5).toFloat()
                findViewById<TextView>(R.id.padText5).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolVolume5.toString().replace("f", "") + " " + padText5 + " " + soundPoolTempo5.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText5).text = soundPoolTempo5.toString().replace("f", "") + " " + padText5 + " " + soundPoolVolume5.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound5, soundPoolVolume5, soundPoolVolume5, 1, 0, soundPoolTempo5)
        }
        findViewById<ImageButton>(R.id.volume_minus6).setOnClickListener {
            if (soundPoolVolume6 > 0.1f) {
                soundPoolVolume6 -= 0.1f
                soundPoolVolume6 = "%.1f".format(soundPoolVolume6).toFloat()
                findViewById<TextView>(R.id.padText6).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolVolume6.toString().replace("f", "") + " " + padText6 + " " + soundPoolTempo6.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolTempo6.toString().replace("f", "") + " " + padText6 + " " + soundPoolVolume6.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound6, soundPoolVolume6, soundPoolVolume6, 1, 0, soundPoolTempo6)
        }
        findViewById<ImageButton>(R.id.volume_plus6).setOnClickListener {
            if (soundPoolVolume6 < 1.0f) {
                soundPoolVolume6 += 0.1f
                soundPoolVolume6 = "%.1f".format(soundPoolVolume6).toFloat()
                findViewById<TextView>(R.id.padText6).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolVolume6.toString().replace("f", "") + " " + padText6 + " " + soundPoolTempo6.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolTempo6.toString().replace("f", "") + " " + padText6 + " " + soundPoolVolume6.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound6, soundPoolVolume6, soundPoolVolume6, 1, 0, soundPoolTempo6)
        }
        findViewById<ImageButton>(R.id.tempo_minus6).setOnClickListener {
            if (soundPoolTempo6 > 0.2f) {
                soundPoolTempo6 -= 0.1f
                soundPoolTempo6 = "%.1f".format(soundPoolTempo6).toFloat()
                findViewById<TextView>(R.id.padText6).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolVolume6.toString().replace("f", "") + " " + padText6 + " " + soundPoolTempo6.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolTempo6.toString().replace("f", "") + " " + padText6 + " " + soundPoolVolume6.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo6 == 0.2f) {
                soundPoolTempo6 = 0.125f
                findViewById<TextView>(R.id.padText6).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolVolume6.toString().replace("f", "") + " " + padText6 + " " + soundPoolTempo6.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolTempo6.toString().replace("f", "") + " " + padText6 + " " + soundPoolVolume6.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound6, soundPoolVolume6, soundPoolVolume6, 1, 0, soundPoolTempo6)
        }
        findViewById<ImageButton>(R.id.tempo_plus6).setOnClickListener {
            if (soundPoolTempo6 == 0.125f) {
                soundPoolTempo6 = 0.2f
                findViewById<TextView>(R.id.padText6).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolVolume6.toString().replace("f", "") + " " + padText6 + " " + soundPoolTempo6.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolTempo6.toString().replace("f", "") + " " + padText6 + " " + soundPoolVolume6.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo6 < 8.0f) {
                soundPoolTempo6 += 0.1f
                soundPoolTempo6 = "%.1f".format(soundPoolTempo6).toFloat()
                findViewById<TextView>(R.id.padText6).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolVolume6.toString().replace("f", "") + " " + padText6 + " " + soundPoolTempo6.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText6).text = soundPoolTempo6.toString().replace("f", "") + " " + padText6 + " " + soundPoolVolume6.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound6, soundPoolVolume6, soundPoolVolume6, 1, 0, soundPoolTempo6)
        }
        findViewById<ImageButton>(R.id.volume_minus7).setOnClickListener {
            if (soundPoolVolume7 > 0.1f) {
                soundPoolVolume7 -= 0.1f
                soundPoolVolume7 = "%.1f".format(soundPoolVolume7).toFloat()
                findViewById<TextView>(R.id.padText7).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolVolume7.toString().replace("f", "") + " " + padText7 + " " + soundPoolTempo7.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolTempo7.toString().replace("f", "") + " " + padText7 + " " + soundPoolVolume7.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound7, soundPoolVolume7, soundPoolVolume7, 1, 0, soundPoolTempo7)
        }
        findViewById<ImageButton>(R.id.volume_plus7).setOnClickListener {
            if (soundPoolVolume7 < 1.0f) {
                soundPoolVolume7 += 0.1f
                soundPoolVolume7 = "%.1f".format(soundPoolVolume7).toFloat()
                findViewById<TextView>(R.id.padText7).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolVolume7.toString().replace("f", "") + " " + padText7 + " " + soundPoolTempo7.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolTempo7.toString().replace("f", "") + " " + padText7 + " " + soundPoolVolume7.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound7, soundPoolVolume7, soundPoolVolume7, 1, 0, soundPoolTempo7)
        }
        findViewById<ImageButton>(R.id.tempo_minus7).setOnClickListener {
            if (soundPoolTempo7 > 0.2f) {
                soundPoolTempo7 -= 0.1f
                soundPoolTempo7 = "%.1f".format(soundPoolTempo7).toFloat()
                findViewById<TextView>(R.id.padText7).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolVolume7.toString().replace("f", "") + " " + padText7 + " " + soundPoolTempo7.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolTempo7.toString().replace("f", "") + " " + padText7 + " " + soundPoolVolume7.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo7 == 0.2f) {
                soundPoolTempo7 = 0.125f
                findViewById<TextView>(R.id.padText7).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolVolume7.toString().replace("f", "") + " " + padText7 + " " + soundPoolTempo7.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolTempo7.toString().replace("f", "") + " " + padText7 + " " + soundPoolVolume7.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound7, soundPoolVolume7, soundPoolVolume7, 1, 0, soundPoolTempo7)
        }
        findViewById<ImageButton>(R.id.tempo_plus7).setOnClickListener {
            if (soundPoolTempo7 == 0.125f) {
                soundPoolTempo7 = 0.2f
                findViewById<TextView>(R.id.padText7).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolVolume7.toString().replace("f", "") + " " + padText7 + " " + soundPoolTempo7.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolTempo7.toString().replace("f", "") + " " + padText7 + " " + soundPoolVolume7.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo7 < 8.0f) {
                soundPoolTempo7 += 0.1f
                soundPoolTempo7 = "%.1f".format(soundPoolTempo7).toFloat()
                findViewById<TextView>(R.id.padText7).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolVolume7.toString().replace("f", "") + " " + padText7 + " " + soundPoolTempo7.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText7).text = soundPoolTempo7.toString().replace("f", "") + " " + padText7 + " " + soundPoolVolume7.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound7, soundPoolVolume7, soundPoolVolume7, 1, 0, soundPoolTempo7)
        }
        findViewById<ImageButton>(R.id.volume_minus8).setOnClickListener {
            if (soundPoolVolume8 > 0.1f) {
                soundPoolVolume8 -= 0.1f
                soundPoolVolume8 = "%.1f".format(soundPoolVolume8).toFloat()
                findViewById<TextView>(R.id.padText8).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolVolume8.toString().replace("f", "") + " " + padText8 + " " + soundPoolTempo8.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolTempo8.toString().replace("f", "") + " " + padText8 + " " + soundPoolVolume8.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound8, soundPoolVolume8, soundPoolVolume8, 1, 0, soundPoolTempo8)
        }
        findViewById<ImageButton>(R.id.volume_plus8).setOnClickListener {
            if (soundPoolVolume8 < 1.0f) {
                soundPoolVolume8 += 0.1f
                soundPoolVolume8 = "%.1f".format(soundPoolVolume8).toFloat()
                findViewById<TextView>(R.id.padText8).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolVolume8.toString().replace("f", "") + " " + padText8 + " " + soundPoolTempo8.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolTempo8.toString().replace("f", "") + " " + padText8 + " " + soundPoolVolume8.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound8, soundPoolVolume8, soundPoolVolume8, 1, 0, soundPoolTempo8)
        }
        findViewById<ImageButton>(R.id.tempo_minus8).setOnClickListener {
            if (soundPoolTempo8 > 0.2f) {
                soundPoolTempo8 -= 0.1f
                soundPoolTempo8 = "%.1f".format(soundPoolTempo8).toFloat()
                findViewById<TextView>(R.id.padText8).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolVolume8.toString().replace("f", "") + " " + padText8 + " " + soundPoolTempo8.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolTempo8.toString().replace("f", "") + " " + padText8 + " " + soundPoolVolume8.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo8 == 0.2f) {
                soundPoolTempo8 = 0.125f
                findViewById<TextView>(R.id.padText8).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolVolume8.toString().replace("f", "") + " " + padText8 + " " + soundPoolTempo8.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolTempo8.toString().replace("f", "") + " " + padText8 + " " + soundPoolVolume8.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound8, soundPoolVolume8, soundPoolVolume8, 1, 0, soundPoolTempo8)
        }
        findViewById<ImageButton>(R.id.tempo_plus8).setOnClickListener {
            if (soundPoolTempo8 == 0.125f) {
                soundPoolTempo8 = 0.2f
                findViewById<TextView>(R.id.padText8).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolVolume8.toString().replace("f", "") + " " + padText8 + " " + soundPoolTempo8.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolTempo8.toString().replace("f", "") + " " + padText8 + " " + soundPoolVolume8.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo8 < 8.0f) {
                soundPoolTempo8 += 0.1f
                soundPoolTempo8 = "%.1f".format(soundPoolTempo8).toFloat()
                findViewById<TextView>(R.id.padText8).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolVolume8.toString().replace("f", "") + " " + padText8 + " " + soundPoolTempo8.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText8).text = soundPoolTempo8.toString().replace("f", "") + " " + padText8 + " " + soundPoolVolume8.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound8, soundPoolVolume8, soundPoolVolume8, 1, 0, soundPoolTempo8)
        }
        findViewById<ImageButton>(R.id.volume_minus9).setOnClickListener {
            if (soundPoolVolume9 > 0.1f) {
                soundPoolVolume9 -= 0.1f
                soundPoolVolume9 = "%.1f".format(soundPoolVolume9).toFloat()
                findViewById<TextView>(R.id.padText9).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolVolume9.toString().replace("f", "") + " " + padText9 + " " + soundPoolTempo9.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolTempo9.toString().replace("f", "") + " " + padText9 + " " + soundPoolVolume9.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound9, soundPoolVolume9, soundPoolVolume9, 1, 0, soundPoolTempo9)
        }
        findViewById<ImageButton>(R.id.volume_plus9).setOnClickListener {
            if (soundPoolVolume9 < 1.0f) {
                soundPoolVolume9 += 0.1f
                soundPoolVolume9 = "%.1f".format(soundPoolVolume9).toFloat()
                findViewById<TextView>(R.id.padText9).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolVolume9.toString().replace("f", "") + " " + padText9 + " " + soundPoolTempo9.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolTempo9.toString().replace("f", "") + " " + padText9 + " " + soundPoolVolume9.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound9, soundPoolVolume9, soundPoolVolume9, 1, 0, soundPoolTempo9)
        }
        findViewById<ImageButton>(R.id.tempo_minus9).setOnClickListener {
            if (soundPoolTempo9 > 0.2f) {
                soundPoolTempo9 -= 0.1f
                soundPoolTempo9 = "%.1f".format(soundPoolTempo9).toFloat()
                findViewById<TextView>(R.id.padText9).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolVolume9.toString().replace("f", "") + " " + padText9 + " " + soundPoolTempo9.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolTempo9.toString().replace("f", "") + " " + padText9 + " " + soundPoolVolume9.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo9 == 0.2f) {
                soundPoolTempo9 = 0.125f
                findViewById<TextView>(R.id.padText9).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolVolume9.toString().replace("f", "") + " " + padText9 + " " + soundPoolTempo9.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolTempo9.toString().replace("f", "") + " " + padText9 + " " + soundPoolVolume9.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound9, soundPoolVolume9, soundPoolVolume9, 1, 0, soundPoolTempo9)
        }
        findViewById<ImageButton>(R.id.tempo_plus9).setOnClickListener {
            if (soundPoolTempo9 == 0.125f) {
                soundPoolTempo9 = 0.2f
                findViewById<TextView>(R.id.padText9).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolVolume9.toString().replace("f", "") + " " + padText9 + " " + soundPoolTempo9.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolTempo9.toString().replace("f", "") + " " + padText9 + " " + soundPoolVolume9.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo9 < 8.0f) {
                soundPoolTempo9 += 0.1f
                soundPoolTempo9 = "%.1f".format(soundPoolTempo9).toFloat()
                findViewById<TextView>(R.id.padText9).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolVolume9.toString().replace("f", "") + " " + padText9 + " " + soundPoolTempo9.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText9).text = soundPoolTempo9.toString().replace("f", "") + " " + padText9 + " " + soundPoolVolume9.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound9, soundPoolVolume9, soundPoolVolume9, 1, 0, soundPoolTempo9)
        }
        findViewById<ImageButton>(R.id.volume_minus10).setOnClickListener {
            if (soundPoolVolume10 > 0.1f) {
                soundPoolVolume10 -= 0.1f
                soundPoolVolume10 = "%.1f".format(soundPoolVolume10).toFloat()
                findViewById<TextView>(R.id.padText10).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolVolume10.toString().replace("f", "") + " " + padText10 + " " + soundPoolTempo10.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolTempo10.toString().replace("f", "") + " " + padText10 + " " + soundPoolVolume10.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound10, soundPoolVolume10, soundPoolVolume10, 1, 0, soundPoolTempo10)
        }
        findViewById<ImageButton>(R.id.volume_plus10).setOnClickListener {
            if (soundPoolVolume10 < 1.0f) {
                soundPoolVolume10 += 0.1f
                soundPoolVolume10 = "%.1f".format(soundPoolVolume10).toFloat()
                findViewById<TextView>(R.id.padText10).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolVolume10.toString().replace("f", "") + " " + padText10 + " " + soundPoolTempo10.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolTempo10.toString().replace("f", "") + " " + padText10 + " " + soundPoolVolume10.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound10, soundPoolVolume10, soundPoolVolume10, 1, 0, soundPoolTempo10)
        }
        findViewById<ImageButton>(R.id.tempo_minus10).setOnClickListener {
            if (soundPoolTempo10 > 0.2f) {
                soundPoolTempo10 -= 0.1f
                soundPoolTempo10 = "%.1f".format(soundPoolTempo10).toFloat()
                findViewById<TextView>(R.id.padText10).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolVolume10.toString().replace("f", "") + " " + padText10 + " " + soundPoolTempo10.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolTempo10.toString().replace("f", "") + " " + padText10 + " " + soundPoolVolume10.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo10 == 0.2f) {
                soundPoolTempo10 = 0.125f
                findViewById<TextView>(R.id.padText10).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolVolume10.toString().replace("f", "") + " " + padText10 + " " + soundPoolTempo10.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolTempo10.toString().replace("f", "") + " " + padText10 + " " + soundPoolVolume10.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound10, soundPoolVolume10, soundPoolVolume10, 1, 0, soundPoolTempo10)
        }
        findViewById<ImageButton>(R.id.tempo_plus10).setOnClickListener {
            if (soundPoolTempo10 == 0.125f) {
                soundPoolTempo10 = 0.2f
                findViewById<TextView>(R.id.padText10).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolVolume10.toString().replace("f", "") + " " + padText10 + " " + soundPoolTempo10.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolTempo10.toString().replace("f", "") + " " + padText10 + " " + soundPoolVolume10.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo10 < 8.0f) {
                soundPoolTempo10 += 0.1f
                soundPoolTempo10 = "%.1f".format(soundPoolTempo10).toFloat()
                findViewById<TextView>(R.id.padText10).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolVolume10.toString().replace("f", "") + " " + padText10 + " " + soundPoolTempo10.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText10).text = soundPoolTempo10.toString().replace("f", "") + " " + padText10 + " " + soundPoolVolume10.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound10, soundPoolVolume10, soundPoolVolume10, 1, 0, soundPoolTempo10)
        }
        findViewById<ImageButton>(R.id.volume_minus11).setOnClickListener {
            if (soundPoolVolume11 > 0.1f) {
                soundPoolVolume11 -= 0.1f
                soundPoolVolume11 = "%.1f".format(soundPoolVolume11).toFloat()
                findViewById<TextView>(R.id.padText11).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolVolume11.toString().replace("f", "") + " " + padText11 + " " + soundPoolTempo11.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolTempo11.toString().replace("f", "") + " " + padText11 + " " + soundPoolVolume11.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound11, soundPoolVolume11, soundPoolVolume11, 1, 0, soundPoolTempo11)
        }
        findViewById<ImageButton>(R.id.volume_plus11).setOnClickListener {
            if (soundPoolVolume11 < 1.0f) {
                soundPoolVolume11 += 0.1f
                soundPoolVolume11 = "%.1f".format(soundPoolVolume11).toFloat()
                findViewById<TextView>(R.id.padText11).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolVolume11.toString().replace("f", "") + " " + padText11 + " " + soundPoolTempo11.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolTempo11.toString().replace("f", "") + " " + padText11 + " " + soundPoolVolume11.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound11, soundPoolVolume11, soundPoolVolume11, 1, 0, soundPoolTempo11)
        }
        findViewById<ImageButton>(R.id.tempo_minus11).setOnClickListener {
            if (soundPoolTempo11 > 0.2f) {
                soundPoolTempo11 -= 0.1f
                soundPoolTempo11 = "%.1f".format(soundPoolTempo11).toFloat()
                findViewById<TextView>(R.id.padText11).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolVolume11.toString().replace("f", "") + " " + padText11 + " " + soundPoolTempo11.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolTempo11.toString().replace("f", "") + " " + padText11 + " " + soundPoolVolume11.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo11 == 0.2f) {
                soundPoolTempo11 = 0.125f
                findViewById<TextView>(R.id.padText11).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolVolume11.toString().replace("f", "") + " " + padText11 + " " + soundPoolTempo11.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolTempo11.toString().replace("f", "") + " " + padText11 + " " + soundPoolVolume11.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound11, soundPoolVolume11, soundPoolVolume11, 1, 0, soundPoolTempo11)
        }
        findViewById<ImageButton>(R.id.tempo_plus11).setOnClickListener {
            if (soundPoolTempo11 == 0.125f) {
                soundPoolTempo11 = 0.2f
                findViewById<TextView>(R.id.padText11).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolVolume11.toString().replace("f", "") + " " + padText11 + " " + soundPoolTempo11.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolTempo11.toString().replace("f", "") + " " + padText11 + " " + soundPoolVolume11.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo11 < 8.0f) {
                soundPoolTempo11 += 0.1f
                soundPoolTempo11 = "%.1f".format(soundPoolTempo11).toFloat()
                findViewById<TextView>(R.id.padText11).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolVolume11.toString().replace("f", "") + " " + padText11 + " " + soundPoolTempo11.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText11).text = soundPoolTempo11.toString().replace("f", "") + " " + padText11 + " " + soundPoolVolume11.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound11, soundPoolVolume11, soundPoolVolume11, 1, 0, soundPoolTempo11)
        }
        findViewById<ImageButton>(R.id.volume_minus12).setOnClickListener {
            if (soundPoolVolume12 > 0.1f) {
                soundPoolVolume12 -= 0.1f
                soundPoolVolume12 = "%.1f".format(soundPoolVolume12).toFloat()
                findViewById<TextView>(R.id.padText12).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolVolume12.toString().replace("f", "") + " " + padText12 + " " + soundPoolTempo12.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolTempo12.toString().replace("f", "") + " " + padText12 + " " + soundPoolVolume12.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound12, soundPoolVolume12, soundPoolVolume12, 1, 0, soundPoolTempo12)
        }
        findViewById<ImageButton>(R.id.volume_plus12).setOnClickListener {
            if (soundPoolVolume12 < 1.0f) {
                soundPoolVolume12 += 0.1f
                soundPoolVolume12 = "%.1f".format(soundPoolVolume12).toFloat()
                findViewById<TextView>(R.id.padText12).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolVolume12.toString().replace("f", "") + " " + padText12 + " " + soundPoolTempo12.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolTempo12.toString().replace("f", "") + " " + padText12 + " " + soundPoolVolume12.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound12, soundPoolVolume12, soundPoolVolume12, 1, 0, soundPoolTempo12)
        }
        findViewById<ImageButton>(R.id.tempo_minus12).setOnClickListener {
            if (soundPoolTempo12 > 0.2f) {
                soundPoolTempo12 -= 0.1f
                soundPoolTempo12 = "%.1f".format(soundPoolTempo12).toFloat()
                findViewById<TextView>(R.id.padText12).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolVolume12.toString().replace("f", "") + " " + padText12 + " " + soundPoolTempo12.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolTempo12.toString().replace("f", "") + " " + padText12 + " " + soundPoolVolume12.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo12 == 0.2f) {
                soundPoolTempo12 = 0.125f
                findViewById<TextView>(R.id.padText12).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolVolume12.toString().replace("f", "") + " " + padText12 + " " + soundPoolTempo12.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolTempo12.toString().replace("f", "") + " " + padText12 + " " + soundPoolVolume12.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound12, soundPoolVolume12, soundPoolVolume12, 1, 0, soundPoolTempo12)
        }
        findViewById<ImageButton>(R.id.tempo_plus12).setOnClickListener {
            if (soundPoolTempo12 == 0.125f) {
                soundPoolTempo12 = 0.2f
                findViewById<TextView>(R.id.padText12).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolVolume12.toString().replace("f", "") + " " + padText12 + " " + soundPoolTempo12.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolTempo12.toString().replace("f", "") + " " + padText12 + " " + soundPoolVolume12.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo12 < 8.0f) {
                soundPoolTempo12 += 0.1f
                soundPoolTempo12 = "%.1f".format(soundPoolTempo12).toFloat()
                findViewById<TextView>(R.id.padText12).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolVolume12.toString().replace("f", "") + " " + padText12 + " " + soundPoolTempo12.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText12).text = soundPoolTempo12.toString().replace("f", "") + " " + padText12 + " " + soundPoolVolume12.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound12, soundPoolVolume12, soundPoolVolume12, 1, 0, soundPoolTempo12)
        }
        findViewById<ImageButton>(R.id.volume_minus13).setOnClickListener {
            if (soundPoolVolume13 > 0.1f) {
                soundPoolVolume13 -= 0.1f
                soundPoolVolume13 = "%.1f".format(soundPoolVolume13).toFloat()
                findViewById<TextView>(R.id.padText13).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolVolume13.toString().replace("f", "") + " " + padText13 + " " + soundPoolTempo13.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolTempo13.toString().replace("f", "") + " " + padText13 + " " + soundPoolVolume13.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound13, soundPoolVolume13, soundPoolVolume13, 1, 0, soundPoolTempo13)
        }
        findViewById<ImageButton>(R.id.volume_plus13).setOnClickListener {
            if (soundPoolVolume13 < 1.0f) {
                soundPoolVolume13 += 0.1f
                soundPoolVolume13 = "%.1f".format(soundPoolVolume13).toFloat()
                findViewById<TextView>(R.id.padText13).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolVolume13.toString().replace("f", "") + " " + padText13 + " " + soundPoolTempo13.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolTempo13.toString().replace("f", "") + " " + padText13 + " " + soundPoolVolume13.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound13, soundPoolVolume13, soundPoolVolume13, 1, 0, soundPoolTempo13)
        }
        findViewById<ImageButton>(R.id.tempo_minus13).setOnClickListener {
            if (soundPoolTempo13 > 0.2f) {
                soundPoolTempo13 -= 0.1f
                soundPoolTempo13 = "%.1f".format(soundPoolTempo13).toFloat()
                findViewById<TextView>(R.id.padText13).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolVolume13.toString().replace("f", "") + " " + padText13 + " " + soundPoolTempo13.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolTempo13.toString().replace("f", "") + " " + padText13 + " " + soundPoolVolume13.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo13 == 0.2f) {
                soundPoolTempo13 = 0.125f
                findViewById<TextView>(R.id.padText13).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolVolume13.toString().replace("f", "") + " " + padText13 + " " + soundPoolTempo13.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolTempo13.toString().replace("f", "") + " " + padText13 + " " + soundPoolVolume13.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound13, soundPoolVolume13, soundPoolVolume13, 1, 0, soundPoolTempo13)
        }
        findViewById<ImageButton>(R.id.tempo_plus13).setOnClickListener {
            if (soundPoolTempo13 == 0.125f) {
                soundPoolTempo13 = 0.2f
                findViewById<TextView>(R.id.padText13).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolVolume13.toString().replace("f", "") + " " + padText13 + " " + soundPoolTempo13.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolTempo13.toString().replace("f", "") + " " + padText13 + " " + soundPoolVolume13.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo13 < 8.0f) {
                soundPoolTempo13 += 0.1f
                soundPoolTempo13 = "%.1f".format(soundPoolTempo13).toFloat()
                findViewById<TextView>(R.id.padText13).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolVolume13.toString().replace("f", "") + " " + padText13 + " " + soundPoolTempo13.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText13).text = soundPoolTempo13.toString().replace("f", "") + " " + padText13 + " " + soundPoolVolume13.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound13, soundPoolVolume13, soundPoolVolume13, 1, 0, soundPoolTempo13)
        }
        findViewById<ImageButton>(R.id.volume_minus14).setOnClickListener {
            if (soundPoolVolume14 > 0.1f) {
                soundPoolVolume14 -= 0.1f
                soundPoolVolume14 = "%.1f".format(soundPoolVolume14).toFloat()
                findViewById<TextView>(R.id.padText14).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolVolume14.toString().replace("f", "") + " " + padText14 + " " + soundPoolTempo14.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolTempo14.toString().replace("f", "") + " " + padText14 + " " + soundPoolVolume14.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound14, soundPoolVolume14, soundPoolVolume14, 1, 0, soundPoolTempo14)
        }
        findViewById<ImageButton>(R.id.volume_plus14).setOnClickListener {
            if (soundPoolVolume14 < 1.0f) {
                soundPoolVolume14 += 0.1f
                soundPoolVolume14 = "%.1f".format(soundPoolVolume14).toFloat()
                findViewById<TextView>(R.id.padText14).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolVolume14.toString().replace("f", "") + " " + padText14 + " " + soundPoolTempo14.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolTempo14.toString().replace("f", "") + " " + padText14 + " " + soundPoolVolume14.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound14, soundPoolVolume14, soundPoolVolume14, 1, 0, soundPoolTempo14)
        }
        findViewById<ImageButton>(R.id.tempo_minus14).setOnClickListener {
            if (soundPoolTempo14 > 0.2f) {
                soundPoolTempo14 -= 0.1f
                soundPoolTempo14 = "%.1f".format(soundPoolTempo14).toFloat()
                findViewById<TextView>(R.id.padText14).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolVolume14.toString().replace("f", "") + " " + padText14 + " " + soundPoolTempo14.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolTempo14.toString().replace("f", "") + " " + padText14 + " " + soundPoolVolume14.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo14 == 0.2f) {
                soundPoolTempo14 = 0.125f
                findViewById<TextView>(R.id.padText14).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolVolume14.toString().replace("f", "") + " " + padText14 + " " + soundPoolTempo14.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolTempo14.toString().replace("f", "") + " " + padText14 + " " + soundPoolVolume14.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound14, soundPoolVolume14, soundPoolVolume14, 1, 0, soundPoolTempo14)
        }
        findViewById<ImageButton>(R.id.tempo_plus14).setOnClickListener {
            if (soundPoolTempo14 == 0.125f) {
                soundPoolTempo14 = 0.2f
                findViewById<TextView>(R.id.padText14).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolVolume14.toString().replace("f", "") + " " + padText14 + " " + soundPoolTempo14.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolTempo14.toString().replace("f", "") + " " + padText14 + " " + soundPoolVolume14.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo14 < 8.0f) {
                soundPoolTempo14 += 0.1f
                soundPoolTempo14 = "%.1f".format(soundPoolTempo14).toFloat()
                findViewById<TextView>(R.id.padText14).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolVolume14.toString().replace("f", "") + " " + padText14 + " " + soundPoolTempo14.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText14).text = soundPoolTempo14.toString().replace("f", "") + " " + padText14 + " " + soundPoolVolume14.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound14, soundPoolVolume14, soundPoolVolume14, 1, 0, soundPoolTempo14)
        }
        findViewById<ImageButton>(R.id.volume_minus15).setOnClickListener {
            if (soundPoolVolume15 > 0.1f) {
                soundPoolVolume15 -= 0.1f
                soundPoolVolume15 = "%.1f".format(soundPoolVolume15).toFloat()
                findViewById<TextView>(R.id.padText15).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolVolume15.toString().replace("f", "") + " " + padText15 + " " + soundPoolTempo15.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolTempo15.toString().replace("f", "") + " " + padText15 + " " + soundPoolVolume15.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound15, soundPoolVolume15, soundPoolVolume15, 1, 0, soundPoolTempo15)
        }
        findViewById<ImageButton>(R.id.volume_plus15).setOnClickListener {
            if (soundPoolVolume15 < 1.0f) {
                soundPoolVolume15 += 0.1f
                soundPoolVolume15 = "%.1f".format(soundPoolVolume15).toFloat()
                findViewById<TextView>(R.id.padText15).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolVolume15.toString().replace("f", "") + " " + padText15 + " " + soundPoolTempo15.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolTempo15.toString().replace("f", "") + " " + padText15 + " " + soundPoolVolume15.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound15, soundPoolVolume15, soundPoolVolume15, 1, 0, soundPoolTempo15)
        }
        findViewById<ImageButton>(R.id.tempo_minus15).setOnClickListener {
            if (soundPoolTempo15 > 0.2f) {
                soundPoolTempo15 -= 0.1f
                soundPoolTempo15 = "%.1f".format(soundPoolTempo15).toFloat()
                findViewById<TextView>(R.id.padText15).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolVolume15.toString().replace("f", "") + " " + padText15 + " " + soundPoolTempo15.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolTempo15.toString().replace("f", "") + " " + padText15 + " " + soundPoolVolume15.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo15 == 0.2f) {
                soundPoolTempo15 = 0.125f
                findViewById<TextView>(R.id.padText15).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolVolume15.toString().replace("f", "") + " " + padText15 + " " + soundPoolTempo15.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolTempo15.toString().replace("f", "") + " " + padText15 + " " + soundPoolVolume15.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound15, soundPoolVolume15, soundPoolVolume15, 1, 0, soundPoolTempo15)
        }
        findViewById<ImageButton>(R.id.tempo_plus15).setOnClickListener {
            if (soundPoolTempo15 == 0.125f) {
                soundPoolTempo15 = 0.2f
                findViewById<TextView>(R.id.padText15).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolVolume15.toString().replace("f", "") + " " + padText15 + " " + soundPoolTempo15.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolTempo15.toString().replace("f", "") + " " + padText15 + " " + soundPoolVolume15.toString().replace("f", "").uppercase()
                }
            } else if (soundPoolTempo15 < 8.0f) {
                soundPoolTempo15 += 0.1f
                soundPoolTempo15 = "%.1f".format(soundPoolTempo15).toFloat()
                findViewById<TextView>(R.id.padText15).text = ""
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolVolume15.toString().replace("f", "") + " " + padText15 + " " + soundPoolTempo15.toString().replace("f", "").uppercase()
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    findViewById<TextView>(R.id.padText15).text = soundPoolTempo15.toString().replace("f", "") + " " + padText15 + " " + soundPoolVolume15.toString().replace("f", "").uppercase()
                }
            }
            soundPool.play(sound15, soundPoolVolume15, soundPoolVolume15, 1, 0, soundPoolTempo15)
        }
    }

    private fun stickyImmersiveMode() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        decorView.setOnSystemUiVisibilityChangeListener { visibility -> // Note that system bars will only be "visible" if none of the
            // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                Log.d("debug", "The system bars are visible")
            } else {
                Log.d("debug", "The system bars are NOT visible")
            }
        }
    }

    private fun loadRewardedAd() {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("rewarded ads", adError.message)
                mRewardedAd = null
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                Log.d("rewarded ads", "Ad was loaded.")
                mRewardedAd = rewardedAd
            }
        })

        mRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d("rewarded ads", "Ad was dismissed.")
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Log.d("rewarded ads", "Ad failed to show.")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d("rewarded ads", "Ad showed fullscreen content.")
                mRewardedAd = null
            }
        }
    }

    private fun showRewardAd() {
        if (mRewardedAd != null) {
            mRewardedAd?.show(this) { rewardItem ->
                binding.adView.visibility = View.GONE
                binding.gridView.visibility = View.INVISIBLE
                adCheck = 1
                stickyImmersiveMode()
                var rewardAmount = rewardItem.amount
                var rewardType = rewardItem.type
                Log.d("TAG", rewardItem.toString())
                Log.d("TAG", "User earned the reward.")
            }
        } else {
            stickyImmersiveMode()
            Log.d("TAG", "The rewarded ad wasn't ready yet.")
        }
    }

    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density
            var adWidthPixels = adViewContainer.width.toFloat()
            if (adWidthPixels == 0.0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }
            val adWidth = (adWidthPixels / density).toInt()


            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this@MainActivity, adWidth)
        }

    private fun initAdMob() {
        adViewContainer = findViewById(R.id.adView)

        MobileAds.initialize(this) {}
        admobmAdView = AdView(this)
        admobmAdView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        admobmAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adViewContainer.addView(admobmAdView)
            }
        }
    }

    private fun loadAdMob() {
        val request = AdRequest.Builder().build()
        admobmAdView.adSize = adSize
        admobmAdView.loadAd(request)
    }

    private fun effect(imageView: ImageView, mpDuration: Int) {
        val cx = imageView.width / 2
        val cy = imageView.height / 2

        val initialRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, initialRadius, 0f)

        anim.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                imageView.setColorFilter(Color.parseColor("#e2e3e3"), PorterDuff.Mode.SRC_IN)
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                imageView.setColorFilter(Color.parseColor("#e2e3e3"), PorterDuff.Mode.SRC_IN)
            }
        })

        anim.duration = mpDuration.toLong()
        anim.start()
    }

    override fun clicked(soundList: SoundList) {
        sound16 = if (buttonB == 1) {
            soundPool.load(soundList.name, 1)
        } else {
            soundPool.load(assets.openFd(soundList.name), 1)
        }
            soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun clicked2(soundList: SoundList) {
        try {
            when {
                buttonA == 1 && buttonB == 1 -> {
                    effect(binding.imageView,400)
                    sound1 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText1 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume = 0.5f
                    soundPoolTempo = 1.0f
                    findViewById<TextView>(R.id.padText1).text = padText1
                }
                buttonA == 2 && buttonB == 1 -> {
                    effect(binding.imageView2,400)
                    sound2 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView2.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText2 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume2 = 0.5f
                    soundPoolTempo2 = 1.0f
                    findViewById<TextView>(R.id.padText2).text = padText2
                }
                buttonA == 3 && buttonB == 1 -> {
                    effect(binding.imageView3,400)
                    sound3 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView3.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText3 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume3 = 0.5f
                    soundPoolTempo3 = 1.0f
                    findViewById<TextView>(R.id.padText3).text = padText3
                }
                buttonA == 4 && buttonB == 1 -> {
                    effect(binding.imageView4,400)
                    sound4 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView4.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText4 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume4 = 0.5f
                    soundPoolTempo4 = 1.0f
                    findViewById<TextView>(R.id.padText4).text = padText4
                }
                buttonA == 5 && buttonB == 1 -> {
                    effect(binding.imageView5,400)
                    sound5 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView5.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText5 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume5 = 0.5f
                    soundPoolTempo5 = 1.0f
                    findViewById<TextView>(R.id.padText5).text = padText5
                }
                buttonA == 6 && buttonB == 1 -> {
                    effect(binding.imageView6,400)
                    sound6 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView6.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText6 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume6 = 0.5f
                    soundPoolTempo6 = 1.0f
                    findViewById<TextView>(R.id.padText6).text = padText6
                }
                buttonA == 7 && buttonB == 1 -> {
                    effect(binding.imageView7,400)
                    sound7 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView7.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText7 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume7 = 0.5f
                    soundPoolTempo7 = 1.0f
                    findViewById<TextView>(R.id.padText7).text = padText7
                }
                buttonA == 8 && buttonB == 1 -> {
                    effect(binding.imageView8,400)
                    sound8 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView8.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText8 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume8 = 0.5f
                    soundPoolTempo8 = 1.0f
                    findViewById<TextView>(R.id.padText8).text = padText8
                }
                buttonA == 9 && buttonB == 1 -> {
                    effect(binding.imageView9,400)
                    sound9 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView9.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText9 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume9 = 0.5f
                    soundPoolTempo9 = 1.0f
                    findViewById<TextView>(R.id.padText9).text = padText9
                }
                buttonA == 10 && buttonB == 1 -> {
                    effect(binding.imageView10,400)
                    sound10 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView10.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText10 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume10 = 0.5f
                    soundPoolTempo10 = 1.0f
                    findViewById<TextView>(R.id.padText10).text = padText10
                }
                buttonA == 11 && buttonB == 1 -> {
                    effect(binding.imageView11,400)
                    sound11 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView11.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText11 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume11 = 0.5f
                    soundPoolTempo11 = 1.0f
                    findViewById<TextView>(R.id.padText11).text = padText11
                }
                buttonA == 12 && buttonB == 1 -> {
                    effect(binding.imageView12,400)
                    sound12 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView12.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText12 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume12 = 0.5f
                    soundPoolTempo12 = 1.0f
                    findViewById<TextView>(R.id.padText12).text = padText12
                }
                buttonA == 13 && buttonB == 1 -> {
                    effect(binding.imageView13,400)
                    sound13 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView13.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText13 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume13 = 0.5f
                    soundPoolTempo13 = 1.0f
                    findViewById<TextView>(R.id.padText13).text = padText13
                }
                buttonA == 14 && buttonB == 1 -> {
                    effect(binding.imageView14,400)
                    sound14 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView14.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText14 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume14 = 0.5f
                    soundPoolTempo14 = 1.0f
                    findViewById<TextView>(R.id.padText14).text = padText14
                }
                buttonA == 15 && buttonB == 1 -> {
                    effect(binding.imageView15,400)
                    sound15 = soundPool.load(soundList.name, 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView15.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText15 = " " + soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume15 = 0.5f
                    soundPoolTempo15 = 1.0f
                    findViewById<TextView>(R.id.padText15).text = padText15
                }
                buttonA == 16 && buttonB == 1 -> {
                    lmp.release()
                    lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(soundList.name))
                    lmp.stop()
                    count = 0.5f
                    bpm = 1.0f
                    actionTitle = soundList.name.replaceBeforeLast("/", "").replace("/", "")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase() + " loop"
                    supportActionBar?.title = actionTitle
                    findViewById<TextView>(R.id.padText0).text = actionTitle
                    soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                }
                buttonA == 1 && buttonB == 2 -> {
                    effect(binding.imageView,400)
                    sound1 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView.text = " " + soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText1 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume = 0.5f
                    soundPoolTempo = 1.0f
                    findViewById<TextView>(R.id.padText1).text = padText1
                }
                buttonA == 2 && buttonB == 2 -> {
                    effect(binding.imageView2,400)
                    sound2 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView2.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText2 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume2 = 0.5f
                    soundPoolTempo2 = 1.0f
                    findViewById<TextView>(R.id.padText2).text = padText2
                }
                buttonA == 3 && buttonB == 2 -> {
                    effect(binding.imageView3,400)
                    sound3 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView3.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText3 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume3 = 0.5f
                    soundPoolTempo3 = 1.0f
                    findViewById<TextView>(R.id.padText3).text = padText3
                }
                buttonA == 4 && buttonB == 2 -> {
                    effect(binding.imageView4,400)
                    sound4 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView4.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText4 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume4 = 0.5f
                    soundPoolTempo4 = 1.0f
                    findViewById<TextView>(R.id.padText4).text = padText4
                }
                buttonA == 5 && buttonB == 2 -> {
                    effect(binding.imageView5,400)
                    sound5 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView5.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText5 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume5 = 0.5f
                    soundPoolTempo5 = 1.0f
                    findViewById<TextView>(R.id.padText5).text = padText5
                }
                buttonA == 6 && buttonB == 2 -> {
                    effect(binding.imageView6,400)
                    sound6 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView6.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText6 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume6 = 0.5f
                    soundPoolTempo6 = 1.0f
                    findViewById<TextView>(R.id.padText6).text = padText6
                }
                buttonA == 7 && buttonB == 2 -> {
                    effect(binding.imageView7,400)
                    sound7 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView7.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText7 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume7 = 0.5f
                    soundPoolTempo7 = 1.0f
                    findViewById<TextView>(R.id.padText7).text = padText7
                }
                buttonA == 8 && buttonB == 2 -> {
                    effect(binding.imageView8,400)
                    sound8 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView8.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText8 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume8 = 0.5f
                    soundPoolTempo8 = 1.0f
                    findViewById<TextView>(R.id.padText8).text = padText8
                }
                buttonA == 9 && buttonB == 2 -> {
                    effect(binding.imageView9,400)
                    sound9 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView9.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText9 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume9 = 0.5f
                    soundPoolTempo9 = 1.0f
                    findViewById<TextView>(R.id.padText9).text = padText9
                }
                buttonA == 10 && buttonB == 2 -> {
                    effect(binding.imageView10,400)
                    sound10 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView10.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText10 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume10 = 0.5f
                    soundPoolTempo10 = 1.0f
                    findViewById<TextView>(R.id.padText10).text = padText10
                }
                buttonA == 11 && buttonB == 2 -> {
                    effect(binding.imageView11,400)
                    sound11 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView11.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText11 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume11 = 0.5f
                    soundPoolTempo11 = 1.0f
                    findViewById<TextView>(R.id.padText11).text = padText11
                }
                buttonA == 12 && buttonB == 2 -> {
                    effect(binding.imageView12,400)
                    sound12 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView12.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText12 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume12 = 0.5f
                    soundPoolTempo12 = 1.0f
                    findViewById<TextView>(R.id.padText12).text = padText12
                }
                buttonA == 13 && buttonB == 2 -> {
                    effect(binding.imageView13,400)
                    sound13 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView13.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText13 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume13 = 0.5f
                    soundPoolTempo13 = 1.0f
                    findViewById<TextView>(R.id.padText13).text = padText13
                }
                buttonA == 14 && buttonB == 2 -> {
                    effect(binding.imageView14,400)
                    sound14 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView14.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText14 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume14 = 0.5f
                    soundPoolTempo14 = 1.0f
                    findViewById<TextView>(R.id.padText14).text = padText14
                }
                buttonA == 15 && buttonB == 2 -> {
                    effect(binding.imageView15,400)
                    sound15 = soundPool.load(assets.openFd(soundList.name), 1)
                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                    binding.textView15.text = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase()
                    padText15 = soundList.name.replace("tr_8", "TR-8").replace("tr_909", "TR-909")
                        .replaceAfterLast(".", "").replace("_", " ").replace(".","").uppercase()
                    soundPoolVolume15 = 0.5f
                    soundPoolTempo15 = 1.0f
                    findViewById<TextView>(R.id.padText15).text = padText15
                }
                buttonA == 16 -> {
                    lmp.release()
                    lmp = LoopMediaPlayer(this@MainActivity, Uri.parse("android.resource://" + packageName + "/raw/" + soundList.name.replace(".ogg", "")))
                    lmp.stop()
                    count = 0.5f
                    bpm = 1.0f
                    actionTitle = soundList.name.replaceAfterLast(".", "").replace("_", " ").replace("."," ").uppercase() + " loop"
                    supportActionBar?.title = actionTitle
                    findViewById<TextView>(R.id.padText0).text = actionTitle
                    soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                        soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext, R.string.error, Toast.LENGTH_LONG).show()
        }
        findViewById<ListView>(R.id.list_view).visibility = View.INVISIBLE
    }

    private fun isReadExternalStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestReadExternalStoragePermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                stickyImmersiveMode()
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult1,
                        Toast.LENGTH_LONG
                ).show()
            } else {
                stickyImmersiveMode()
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult2,
                        Toast.LENGTH_LONG
                ).show()
            }
        }

        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                stickyImmersiveMode()
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult1,
                        Toast.LENGTH_LONG
                ).show()
            } else {
                stickyImmersiveMode()
                Toast.makeText(
                        this,
                        R.string.onRequestPermissionsResult2,
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val menuLamp = menu!!.findItem(R.id.menu1)
        if (menuSwitch) {
            menuLamp.setIcon(R.drawable.ic_baseline_play_arrow_24)
        } else {
            menuLamp.setIcon(R.drawable.ic_baseline_stop_24)
        }

        val menuLamp2 = menu.findItem(R.id.menu10)
        if (paste == 1) {
            menuLamp2.setIcon(R.drawable.ic_baseline_library_music_24_c)
        } else if (paste == 2) {
            menuLamp2.setIcon(R.drawable.ic_baseline_library_music_24)
        }

        return true
    }

    private var menuSwitch = true
    private var menuSwitch2 = true
    private var switch1 = 0


    @SuppressLint("SimpleDateFormat")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val soundListView = findViewById<ListView>(R.id.list_view)
        val actionGridView = findViewById<GridView>(R.id.grid_view)
        val chooseGridView = findViewById<GridView>(R.id.grid_view_choose)
        val tuningView = findViewById<View>(R.id.view)

        when (item.itemId) {

            R.id.menu1 -> {
                when {
                    soundListView.isVisible -> {
                        soundListView.visibility = View.INVISIBLE
                    }
                    actionGridView.isVisible -> {
                        actionGridView.visibility = View.INVISIBLE
                    }
                    chooseGridView.isVisible -> {
                        chooseGridView.visibility = View.INVISIBLE
                    }
                }
                if (switch1 == 1) {
                    lmp.stop()
                    soundPool.autoPause()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1 = 2
                } else {
                    lmp.start()
                    menuSwitch = false
                    invalidateOptionsMenu()
                    switch1 = 1
                }
                return true
            }

            R.id.menu10 -> {
                when {
                    chooseGridView.isVisible -> {
                        actionGridView.visibility = View.INVISIBLE
                        chooseGridView.visibility = View.INVISIBLE
                        tuningView.visibility = View.INVISIBLE
                    }
                    soundListView.isVisible -> {
                        chooseGridView.visibility = View.VISIBLE
                        soundListView.visibility = View.INVISIBLE
                    }
                    actionGridView.isVisible -> {
                        chooseGridView.visibility = View.VISIBLE
                        actionGridView.visibility = View.INVISIBLE
                        tuningView.visibility = View.INVISIBLE
                    }
                    tuningView.isVisible -> {
                        chooseGridView.visibility = View.VISIBLE
                        actionGridView.visibility = View.INVISIBLE
                        tuningView.visibility = View.INVISIBLE
                    }
                    soundListView.isInvisible && actionGridView.isInvisible && tuningView.isInvisible -> {
                        chooseGridView.visibility = View.VISIBLE
                    }
                }
                return true
            }

            R.id.action_settings -> {
                when {
                    chooseGridView.isVisible -> {
                        actionGridView.visibility = View.VISIBLE
                        chooseGridView.visibility = View.INVISIBLE
                        tuningView.visibility = View.INVISIBLE
                    }
                    soundListView.isVisible -> {
                        actionGridView.visibility = View.VISIBLE
                        soundListView.visibility = View.INVISIBLE
                    }
                    actionGridView.isInvisible && tuningView.isVisible -> {
                        tuningView.visibility = View.INVISIBLE
                    }
                    actionGridView.isInvisible -> {
                        actionGridView.visibility = View.VISIBLE
                    }
                    actionGridView.isVisible -> {
                        actionGridView.visibility = View.INVISIBLE
                        tuningView.visibility = View.INVISIBLE
                    }
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        lmp.reset()
        lmp.release()
        mp.reset()
        mp.release()
        soundPool.autoPause()
        soundPool.release()

        super.onDestroy()
    }

    override fun onPause() {
        menuSwitch = true
        invalidateOptionsMenu()
        switch1 = 2
        if (mp.isPlaying) {
            mp.stop()
            mp.prepare()
        }
        if (!menuSwitch2) {
            menuSwitch2 = true
            invalidateOptionsMenu()
        }

            lmp.stop()
            soundPool.autoPause()

        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("DATA", adCheck)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        adCheck = savedInstanceState.getInt("DATA")
        if ( adCheck == 1) {
            binding.adView.visibility = View.GONE
        }
    }
}
