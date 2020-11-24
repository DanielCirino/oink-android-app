package tech.devrocks.fastfit_gestao.services

import retrofit2.Call
import retrofit2.http.GET

import tech.devrocks.oink.model.RespostaPadraoApi

interface ApiServiceInterface {
  @GET("api/status")
  fun verificarStatusAPI(): Call<RespostaPadraoApi>

}