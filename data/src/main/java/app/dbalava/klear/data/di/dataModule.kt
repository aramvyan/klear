package app.dbalava.klear.data.di

import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File
import java.io.FileInputStream
import java.util.Properties


val dataModule = module {

    //ktor init
    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        encodeDefaults = false
                    }
                )
            }

            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
            }

            defaultRequest {
                val secretsFile = File("secrets.properties")
                val secrets = Properties()
                secrets.load(FileInputStream(secretsFile))
                val apiUrl = secrets.getProperty("API_URL")

                url {
                    protocol = URLProtocol.HTTPS
                    host = apiUrl
                }

                val token = "SomeToken"
                header("Authorization", "Bearer $token")
                header("Accept", "application/json")
            }
        }
    }

    // Room init
    single {
        Room.databaseBuilder(
            androidContext(),
            TerraIncDB::class.java,
            "can_reader_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    //DataStore init
    single {
        provideDataStore(androidContext())
    }

}