package tech.devrocks.fastfit_gestao.services

import retrofit2.Call
import retrofit2.http.*
import tech.devrocks.oink.model.RespostaPadraoApi


interface FinanceiroServiceInterface {
  @GET("financeiro/api/status")
  fun verificarStatusAPI(): Call<RespostaPadraoApi>

//  @POST("financeiro")
//  fun cadastrarMovimentoFinanceiro(@Body movimentoFinanceiro: MovimentoFinanceiro): Call<RespostaPadraoApi>
//
//  @PUT("financeiro")
//  fun atualizarMovimentoFinanceiro(movimentoFinanceiro: MovimentoFinanceiro): Call<RespostaPadraoApi>
//
//  @GET("financeiro")
//  fun obterMovimentacoesFinanceirasCadastradas(): Call<RespostaPadraoApi>
//
//  @GET("financeiro/saldo")
//  fun obterSaldoFinanceiro(): Call<RespostaPadraoApi>
//
//  @GET("financeiro/saldo-contas")
//  fun obterSaldoFinanceiroContas(): Call<RespostaPadraoApi>
//
//  @GET("financeiro/{idMovimentacao}")
//  fun obterMovimentacaoPorId(@Path("idMovimentacao") idMovimentacao: String): Call<RespostaPadraoApi>

}