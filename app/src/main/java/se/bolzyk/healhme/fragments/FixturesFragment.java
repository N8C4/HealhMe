package se.bolzyk.healhme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

import se.bolzyk.healhme.R;

/**
 * Created by Andreas Bolzyk on 28/02/26
 */
public class FixturesFragment extends Fragment {

    // Array of strings...
    private String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    private String[] mNavigationDrawerItemTitles;
    private String[] theObjectAr;

    private ListView mDrawerList;


    static final int READ_BLOCK_SIZE = 100;

    public FixturesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fixtures, container, false);



        //mDrawerList = (ListView) rootView.findViewById(R.id.tempitmes);

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);

        //new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);
        //ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),R.layout.activity_listview, mobileArray);
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),R.layout.activity_listview, mNavigationDrawerItemTitles);

        ListView listView = (ListView) rootView.findViewById(R.id.activity_listview);
        listView.setAdapter(adapter);

        crateFileAndRead(getContext());

        FileAndRead(getContext());

        return rootView;
    }


    public static void crateFileAndRead (Context ctx) {

        String FILENAME = "hello_file";
        String string = "hello world!";

        FileOutputStream fos;
        try {
            fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(string.getBytes());
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void FileAndRead (Context ctx) {

        String FILENAME = "hello_file";

        FileInputStream fileIn;
        try {
            fileIn = ctx.openFileInput(FILENAME);
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
