package tech.devrocks.fastfit_gestao.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "http://3.86.182.11/"
private const val TOKEN_ACESSO = "b0339adc-2384-4894-907f-965a62433ac9"

private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
  val original = chain.request()
  val requestBuilder = original.newBuilder()
    .addHeader("x-access-token", TOKEN_ACESSO)
    .addHeader("Content-Type", "application/json; charset=utf-8")
    .addHeader("accept-encoding", "utf-8")
    .method(original.method(), original.body())

  val request = requestBuilder.build()
  chain.proceed(request)
}.build()

private val gson = GsonBuilder()
  .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
  .create()

private val retrofit = Retrofit.Builder()
  .baseUrl(URL_BASE)
  .addConverterFactory(GsonConverterFactory.create(gson))
  .client(okHttpClient)
  .build()

object RetrofitClient {
  val apiService: ApiServiceInterface by lazy {
    retrofit.create(ApiServiceInterface::class.java)

  }


  val financeiroService: FinanceiroServiceInterface by lazy {
    retrofit.create(FinanceiroServiceInterface::class.java)
  }
  val contaBancariaService: ContaBancariaServiceInterface by lazy {
    retrofit.create(ContaBancariaServiceInterface::class.java)
  }

}