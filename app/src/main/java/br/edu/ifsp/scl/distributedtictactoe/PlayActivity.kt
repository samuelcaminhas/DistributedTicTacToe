package br.edu.ifsp.scl.distributedtictactoe

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.distributedtictactoe.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.b0.setOnClickListener(this)
        binding.b1.setOnClickListener(this)
        binding.b2.setOnClickListener(this)
        binding.b3.setOnClickListener(this)
        binding.b4.setOnClickListener(this)
        binding.b5.setOnClickListener(this)
        binding.b6.setOnClickListener(this)
        binding.b7.setOnClickListener(this)
        binding.b8.setOnClickListener(this)


        binding.StartGameBT.setOnClickListener{
            startGame()
        }

        PlayData.playModel.observe(this) {
           playModel = it
           setUI()
        }

    }

    fun setUI(){
        playModel?.apply {
            binding.b0.text = filledPos[0]
            binding.b1.text = filledPos[1]
            binding.b2.text = filledPos[2]
            binding.b3.text = filledPos[3]
            binding.b4.text = filledPos[4]
            binding.b5.text = filledPos[5]
            binding.b6.text = filledPos[6]
            binding.b7.text = filledPos[7]
            binding.b8.text = filledPos[8]
        }
    }

    fun startGame(){
        startActivity(Intent(this, PlayActivity::class.java))
    }
}