package tech.devrocks.fastfit_gestao.services

import retrofit2.Call
import retrofit2.http.*
import tech.devrocks.oink.model.RespostaPadraoApi


interface ContaBancariaServiceInterface {
  @GET("contas_bancarias/api/status")
  fun verificarStatusAPI(): Call<RespostaPadraoApi>

//  @POST("contas_bancarias")
//  fun cadastrarContaBancaria(@Body conta: ContaBancaria): Call<RespostaPadraoApi>
//
//  @PUT("contas_bancarias")
//  fun atualizarContaBancaria(conta: ContaBancaria): Call<RespostaPadraoApi>
//
//  @GET("contas_bancarias")
//  fun obterContasBancariasCadastradas(): Call<RespostaPadraoApi>
//
//  @GET("contas_bancarias/pesquisa/{textoPesquisa}")
//  fun pesquisarContasBancariasPorNome(@Path("textoPesquisa") textoPesquisa: String): Call<RespostaPadraoApi>
//
//  @GET("contas_bancarias/{idProduto}")
//  fun obterContaBancariaPorId(@Path("idProduto") idProduto: String): Call<RespostaPadraoApi>

}