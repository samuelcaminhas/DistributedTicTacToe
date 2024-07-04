package br.edu.ifsp.scl.distributedtictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object PlayData {
    private var _playModel: MutableList<PlayModel> = MutableLiveData()
    var playModel: LiveData<PlayModel> = _playModel

    fun savePlayModel(model: PlayModel) {

        _playModel.postValue(model)

    }
}