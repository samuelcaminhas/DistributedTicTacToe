package br.edu.ifsp.scl.distributedtictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.distributedtictactoe.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = FirebaseDatabase.getInstance().reference

        binding.newGameBT.setOnClickListener {
            createNewGame()
        }

        binding.joinBT.setOnClickListener {
            joinGame()
        }
    }

    private fun createNewGame() {
        PlayData.ID = "X"
        val gameID = Random.nextInt(100000, 999999).toString()
        val playModel = PlayModel(
            gameID = gameID,
            currentStatus = Status.C
        )
        PlayData.savePlayModel(playModel)
        
        database.child("games").child(gameID).setValue(playModel)
        startPlay()
    }

    private fun joinGame() {
        val gameID = binding.gameIdIn.text.toString()
        if (gameID.isEmpty()) {
            binding.gameIdIn.setError("ID não eh valido, vacilao")
            return
        }
        PlayData.ID = "O"
        database.child("games").child(gameID).get().addOnSuccessListener {
            val model = it.getValue(PlayModel::class.java)
            if (model == null) {
                binding.gameIdIn.setError("ID não eh valido, vacilao")
            } else {
                model.currentStatus = Status.P
                PlayData.savePlayModel(model)

                // Atualizando o status do jogo no Realtime Database
                database.child("games").child(gameID).setValue(model)
                startPlay()
            }
        }.addOnFailureListener {
            binding.gameIdIn.setError("ID não eh valido, vacilao")
        }
    }

    private fun startPlay() {
        startActivity(Intent(this, PlayActivity::class.java))
    }
}
