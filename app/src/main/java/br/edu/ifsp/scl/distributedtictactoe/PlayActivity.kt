package br.edu.ifsp.scl.distributedtictactoe

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.distributedtictactoe.databinding.ActivityPlayBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class PlayActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityPlayBinding
    private var playModel: PlayModel? = null
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = FirebaseDatabase.getInstance().reference

        PlayData.fetchPlayModel()

        binding.b0.setOnClickListener(this)
        binding.b1.setOnClickListener(this)
        binding.b2.setOnClickListener(this)
        binding.b3.setOnClickListener(this)
        binding.b4.setOnClickListener(this)
        binding.b5.setOnClickListener(this)
        binding.b6.setOnClickListener(this)
        binding.b7.setOnClickListener(this)
        binding.b8.setOnClickListener(this)

        binding.StartGameBT.setOnClickListener {
            startGame()
        }

        PlayData.playModel.observe(this) {
            playModel = it
            setUI()
        }
    }

    private fun setUI() {
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

            binding.gameStatusTV.text = when (currentStatus) {
                Status.C -> "Codigo:" + gameID
                Status.F -> "Fim de jogo, foi empate"
                Status.P -> "Vez do jogador: " + roundPlayer
                Status.W -> if (winner.isNotEmpty()) winner + " venceu" else "Empate"
            }
        }


    }

    private fun startGame() {
        playModel?.apply {
            updatePlayData(
                PlayModel(
                    gameID = gameID,
                    currentStatus = Status.P
                )
            )
        }
    }

    private fun checkWinner() {
        val winPos = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6),
        )

        playModel?.apply {
            for (i in winPos) {
                if (
                    filledPos[i[0]] == filledPos[i[1]] &&
                    filledPos[i[1]] == filledPos[i[2]] &&
                    filledPos[i[0]].isNotEmpty()
                ) {
                    currentStatus = Status.W
                    winner = filledPos[i[0]]
                    break
                }

                if(filledPos.none(){ it.isEmpty() }){
                    currentStatus = Status.F
                }
            }
        }
    }

    private fun updatePlayData(model: PlayModel) {
        PlayData.savePlayModel(model)
        database.child("games").child(model.gameID).setValue(model)
    }

    override fun onClick(v: View?) {
        playModel?.apply {
            if (currentStatus != Status.P) {
                Toast.makeText(applicationContext, "Jogo não começou", Toast.LENGTH_SHORT).show()
                return
            }
            val clickedPos = (v?.tag as String).toInt()
            if (filledPos[clickedPos].isEmpty()) {
                filledPos[clickedPos] = roundPlayer
                roundPlayer = if (roundPlayer == "X") "O" else "X"
                checkWinner()
                updatePlayData(this)
            }
        }
    }
}
