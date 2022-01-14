package com.rodrigobn.frameworkdigitale_commerce.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(val name: String = "", val password: String = "") : Parcelable