package br.com.digitalhouse.json.model

import com.google.gson.annotations.SerializedName

data class Teste(@SerializedName("noticias")
                 val noticias: List<NoticiasItem>?)