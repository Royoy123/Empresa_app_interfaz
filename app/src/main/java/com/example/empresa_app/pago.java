package com.example.empresa_app;

import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

public class PaymentActivity extends AppCompatActivity {

    private PaymentsClient paymentsClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Inicializa el cliente de pagos
        paymentsClient = Wallet.getPaymentsClient(
                this,
                new Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                        .build()
        );

        // Configura el método de pago seguro
        PaymentDataRequest request = PaymentDataRequest.fromJson(yourPaymentDataRequestJson);
        AutoResolveHelper.resolveTask(
                paymentsClient.loadPaymentData(request),
                this,
                LOAD_PAYMENT_DATA_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOAD_PAYMENT_DATA_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    // El pago se realizó correctamente, puedes procesar el resultado
                    PaymentData paymentData = PaymentData.getFromIntent(data);
                    handlePaymentSuccess(paymentData);
                    break;
                case Activity.RESULT_CANCELED:
                    // El usuario canceló el proceso de pago
                    handlePaymentCanceled();
                    break;
                case AutoResolveHelper.RESULT_ERROR:
                    // Hubo un error en el proceso de pago
                    Status status = AutoResolveHelper.getStatusFromIntent(data);
                    handleError(status);
                    break;
                default:
                    // Otros casos de resultado
                    break;
            }
        }
    }

    private void handlePaymentSuccess(PaymentData paymentData) {
        // Lógica para procesar el pago exitoso
    }

    private void handlePaymentCanceled() {
        // Lógica para manejar la cancelación del pago
    }

    private void handleError(Status status) {
        // Lógica para manejar errores de pago
    }
}