package model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class tpViewModel:ViewModel() {

    private var _resultMessage=MutableLiveData<String>("-")
    val resultMessage:LiveData<String> = _resultMessage

    private var _resultOk=MutableLiveData<String> ()
    private var _resultFail=MutableLiveData<String>()

    fun checkStrings(textos:ComparadorModel){
        _resultMessage.value= if(textos.primerTxt.equals(textos.segundoTxt,ignoreCase = false) ) _resultOk.value else _resultFail.value
    }

    fun setOkMessage(message:String){
        _resultOk.value=message
    }

    fun setFailMessage(message: String){
        _resultFail.value=message
    }
}