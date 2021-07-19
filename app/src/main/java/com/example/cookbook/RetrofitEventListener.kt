package com.example.cookbook

import retrofit2.Call

interface RetrofitEventListener<T> {
    fun onSuccess(call: Call<T>, response: T)
    fun onError(call: Call<T>, t: Throwable)
}