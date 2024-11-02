package com.sample.bodymassindex

import android.util.Log
import androidx.core.view.ContentInfoCompat.Source
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent


class MyObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun myFun(source: LifecycleOwner, event: Lifecycle.Event){
        Log.d("TAG", event.toString())
    }
}