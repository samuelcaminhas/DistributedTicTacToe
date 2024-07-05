package br.edu.ifsp.scl.distributedtictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

object PlayData {
    private var _playModel: MutableLiveData<PlayModel> = MutableLiveData()
    var playModel: LiveData<PlayModel> = _playModel
    var ID = ""
    private lateinit var database: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance().reference
    }

    fun savePlayModel(model: PlayModel) {
        _playModel.postValue(model)
        if (model.gameID != "-1") {
            database.child("games").child(model.gameID).setValue(model)
        }
    }

    fun fetchPlayModel() {
        playModel.value?.apply {
            if (gameID != "-1") {
                database.child("games").child(gameID).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val model = snapshot.getValue(PlayModel::class.java)
                        _playModel.postValue(model)
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }
        }
    }
}
