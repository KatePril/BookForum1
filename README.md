### Before running the program create files `SecretKeys.kt` and `HashConstants.kt`

Include the following code in the `SecretKeys.kt`:
```
const val XRapidAPIKey = "YOUR_XRapidAPIKey"
```
to get your XRapidAPIKey [follow the link](https://rapidapi.com/riyanshgupta750/api/getbooksinfo)

Include the following code in the `HashConstants.kt`:
```
const val ALGORITHM = "PBKDF2WithHmacSHA512"
const val ITERATIONS = NUMBER_OF_ITERATIONS_INT
const val KEY_LENGTH = KEY_LENGTH_INT
const val SECRET = "Any string you want"
const val SALT = "Any string you want"
```
