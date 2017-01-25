package com.example.intentexcercises;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dean on 25/01/2017.
 */

public class MultipleIntents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multipe_intents_activity);
        getSupportActionBar().setTitle("Multiple Intents");

        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_speechintent:
                Intent intent = new Intent(MultipleIntents.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.action_multipleintents:break;
        }

        return super.onOptionsItemSelected(item);
    }



    @OnClick(R.id.btnOpenWebsite)
    public void openWebsite(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Type in the url");
        final EditText inputWebsite = new EditText(this);
        inputWebsite.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(inputWebsite);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String url = inputWebsite.getText().toString();
                if (url.contains("http://")) {
                    intent.setData(Uri.parse(url));
                }else{
                    intent.setData(Uri.parse("http://"+url));
                }
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @OnClick(R.id.btnOpenContacts)
    public void openContacts(){
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivity(intent);
    }

    @OnClick(R.id.btnOpenDialer)
    public void openDialer(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }

    @OnClick(R.id.btnSearchGoogle)
    public void searchGoogle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What do you want to search?");
        final EditText inputQuery = new EditText(this);
        inputQuery.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(inputQuery);
        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                String query = inputQuery.getText().toString();
                intent.putExtra(SearchManager.QUERY, query);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @OnClick(R.id.btnStartVoiceCommand)
    public void startVoiceCommand(){
        try {
            Intent intent = new Intent(Intent.ACTION_VOICE_COMMAND);
            startActivity(intent);
        }catch(ActivityNotFoundException e){
            e.printStackTrace();
        }
    }
}
