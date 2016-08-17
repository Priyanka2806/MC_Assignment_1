package in.ac.iiitd.psingh.mc16.objectivequiz;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.util.Random;
public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView textQuestion;

    Random rand = new Random();
    int random_num = 0;
    int temp = 0;
    private static final String TAG = "QuizActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "Inside onCreate");
        mTrueButton = (Button) findViewById(R.id.TrueButton);
        mTrueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.d(TAG, "Clicked True");
                temp = 1;
                Context context = getApplicationContext();
                CharSequence text = "";
                boolean value = checkAnswer(random_num);
                if(value == true)
                {
                    text = "Correct";
                }
                else
                    text = "Incorrect";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        mFalseButton = (Button) findViewById(R.id.FalseButton);
        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.d(TAG, "Clicked False");
                temp = 1;
                Context context = getApplicationContext();
                CharSequence text = "";
                boolean value = checkAnswer(random_num);
                if(value == false)
                {
                    text = "Correct";
                }
                else
                    text = "Incorrect";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        if(savedInstanceState!=null)
        {
            random_num = savedInstanceState.getInt(KEY_VALUE);
            Log.d(TAG, "Restoring the Activity");

        }
        else{
            random_num = rand.nextInt(1000) + 1;
        }

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.d(TAG, "Clicked Next");
                if(temp == 0)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Question Unanswered";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    temp = 0;
                    random_num = rand.nextInt(1000) + 1;
                    generatingQues();
                }
            }
        });

        generatingQues();
    }

    public void generatingQues(){
        //random_num = rand.nextInt(1000) + 1;
        String num = Integer.toString(random_num);
        String ques = " is a Prime Number?";
        String question = num + ques;
        textQuestion=(TextView)findViewById(R.id.textViewer);
        textQuestion.setText(question);
    }

    public boolean checkAnswer(int number){
        Log.d(TAG, "Inside CheckAnswer");
        int i,var1=0,flag=0;
        boolean result = true;
        int numberToBeChecked= number;
        var1=numberToBeChecked/2;
        for(i=2;i<=var1;i++){
            if(numberToBeChecked%i==0){
                result = false;
                flag=1;
                break;
            }
        }
        if(flag==0)
            result = true;
        return result;
    }

    private static final String KEY_VALUE = "randomNumberValue";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
        savedInstanceState.putInt(KEY_VALUE, random_num);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }
}
