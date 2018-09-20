package br.com.digitalhouse.json.model

import com.google.gson.annotations.SerializedName

data class NoticiasItem(@SerializedName("date")
                        val date: String = "",
                        @SerializedName("author")
                        val author: String = "",
                        @SerializedName("description")
                        val description: String = "",
                        @SerializedName("title")
                        val title: String = "")