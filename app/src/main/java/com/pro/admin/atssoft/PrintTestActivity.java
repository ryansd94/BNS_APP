package com.pro.admin.atssoft;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.PrintCommon;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class PrintTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_test);
        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Socket sock = new Socket("192.168.1.201", 9100);


                                    PrintWriter oStream = new PrintWriter(sock.getOutputStream());



                                    Object[][] people = {
                                            {"Alice", 123},
                                            {"Bob", 456},
                                            {"Carol", 1111},
                                            {"Ted", 2222},
                                    };

                                    String nameFormat = " %1$-20s ";
                                    String dateFormat = " %2$ ";
                                    String ageFormat = " %3$3s %n";
                                    String line = new String(new char[48]).replace('\0', '-');

                                    oStream.println(line);
                                    oStream.printf("%1$-20s %2$-20s %3$3s %n",
                                            "Name",
                                            "Birth Date",
                                            "Age");
                                    oStream.println(line);

                                    oStream.println("\n\n\n");
                                    oStream.println(new char[]{0x1D, 0x56, 0x41, 0x10});
                                    oStream.flush();
                                    oStream.close();
                                    sock.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                //thread.start();


                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Socket sock = new Socket("192.168.1.201", 9100);

                            PrintWriter oStream = new PrintWriter(sock.getOutputStream());
                            Bitmap bmp=getBitmapFromURL("http://phanmem.giaiphapantam.com/bill/test.png");
                            if(bmp!=null){
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] byteArray = stream.toByteArray();
                                sock.getOutputStream().write(byteArray);
                            }

                            oStream.println(new char[]{0x1D, 0x56, 0x41, 0x10});
                            oStream.flush();
                            oStream.close();

                            sock.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread2.start();
            }
        });
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }



}
