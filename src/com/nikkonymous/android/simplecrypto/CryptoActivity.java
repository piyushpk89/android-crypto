package com.nikkonymous.android.simplecrypto;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CryptoActivity extends Activity implements OnClickListener{
	private EditText txtOriginal, txtEncrypted, txtDecrypted;
	private Button btnEncrypt, btnDecrypt;
	final String SecretKey = "9835764521hgfdsa"; //This serves as your security key. You can customize this at any time
	final String iv = "asdfgh1254675389"; //You can customize this at any time
	CryptoHelper crypto = new CryptoHelper(SecretKey, iv); //Constructor of helper class 'CryptoHelper'
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txtOriginal = (EditText) this.findViewById(R.id.txtOriginal);
        txtEncrypted = (EditText) this.findViewById(R.id.txtEncrypted);
        txtDecrypted = (EditText) this.findViewById(R.id.txtDecrypted);
        btnEncrypt = (Button) this.findViewById(R.id.btnEncrypt);
        btnEncrypt.setOnClickListener(this);
        btnDecrypt = (Button) this.findViewById(R.id.btnDecrypt);
        btnDecrypt.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnEncrypt:
			try {
				String original = txtOriginal.getText().toString();
				/*Encrypts the original text to bytes and converts it into hex string*/
				String encrypted = CryptoHelper.bytesToHex(crypto.encrypt(original));
				txtEncrypted.setText(encrypted);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.btnDecrypt:
			try {
				String encryptedText = txtEncrypted.getText().toString();
				/*Decrypts the encrypted string text*/
				String decrypted = new String(crypto.decrypt(encryptedText));
				txtDecrypted.setText(decrypted);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		default:
			
			break;
		}
	}
    
}
