# Weather Forecast For 48 Hours

Bu uygulama, OpenWeather API kullanılarak bir şehrin önümüzdeki 48 saatlik hava durumunu 3 saatlik periodlar halinde tahminini sağlar.
-Java 17 
-Spring Boot 3.2.3
-Maven 3.8.1

## Kullanım

Uygulama, OpenWeather API'den hava durumu verilerini çeker. 5 Day / 3 Hour Forecast API'sini kullanır.

1. **API Anahtarı Ayarı**

    `application.properties` dosyasındaki `openweathermap.api.key` özelliğini kendi OpenWeather API anahtarınızla ayarlayın.
   
    openweathermap.api.key=YOUR_OPENWEATHER_API_KEY

2. **Uygulamayı Çalıştırma**

    Uygulamayı aşağıdaki adımları takip ederek başlatabilirsiniz:

    ```bash
    ./mvnw spring-boot:run
    ```
    Uygulama başlatıldıktan sonra, `http://localhost:8080/swagger-ui/index.html#/` adresine giderek hava durumu tahminlerini alabilirsiniz.

## API Kullanımı

Uygulama, RESTful bir API sunar. Hava durumu tahminlerini almak için aşağıdaki endpoint'i kullanabilirsiniz:

- `GET /weather?city={CITY_NAME}`: Belirtilen şehrin önümüzdeki 48 saatlik hava durumu tahminini 3 saatlik periodlar halinde alır.
