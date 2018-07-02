package com.example.android.quizzapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.quizzapp.R;
import com.example.android.quizzapp.appconstants.AppConstantStrings;
import com.example.android.quizzapp.customviews.CustomButton;
import com.example.android.quizzapp.listeners.Callbacks;
import com.example.android.quizzapp.ui.dialogs.MyAlertDialog;
import com.example.android.quizzapp.utils.AppKeys;
import com.example.android.quizzapp.utils.UtilMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //region XML Parsed UI Variables
    @BindView(R.id.rbtn_ans_1_a)
    RadioButton rbtnAns1A;
    @BindView(R.id.rbtn_ans_1_b)
    RadioButton rbtnAns1B;
    @BindView(R.id.rbtn_ans_1_c)
    RadioButton rbtnAns1C;
    @BindView(R.id.rbtn_ans_1_d)
    RadioButton rbtnAns1D;
    @BindView(R.id.rbtn_ans_2_a)
    RadioButton rbtnAns2A;
    @BindView(R.id.rbtn_ans_2_b)
    RadioButton rbtnAns2B;
    @BindView(R.id.rbtn_ans_2_c)
    RadioButton rbtnAns2C;
    @BindView(R.id.rbtn_ans_2_d)
    RadioButton rbtnAns2D;
    @BindView(R.id.rbtn_ans_3_a)
    RadioButton rbtnAns3A;
    @BindView(R.id.rbtn_ans_3_b)
    RadioButton rbtnAns3B;
    @BindView(R.id.rbtn_ans_3_c)
    RadioButton rbtnAns3C;
    @BindView(R.id.rbtn_ans_3_d)
    RadioButton rbtnAns3D;
    @BindView(R.id.cb_ans_4_a)
    CheckBox cbAns4A;
    @BindView(R.id.cb_ans_4_b)
    CheckBox cbAns4B;
    @BindView(R.id.cb_ans_4_c)
    CheckBox cbAns4C;
    @BindView(R.id.cb_ans_4_d)
    CheckBox cbAns4D;
    @BindView(R.id.rbtn_ans_5_a)
    RadioButton rbtnAns5A;
    @BindView(R.id.rbtn_ans_5_b)
    RadioButton rbtnAns5B;
    @BindView(R.id.rbtn_ans_5_c)
    RadioButton rbtnAns5C;
    @BindView(R.id.rbtn_ans_5_d)
    RadioButton rbtnAns5D;
    @BindView(R.id.rbtn_ans_6_a)
    RadioButton rbtnAns6A;
    @BindView(R.id.rbtn_ans_6_b)
    RadioButton rbtnAns6B;
    @BindView(R.id.rbtn_ans_6_c)
    RadioButton rbtnAns6C;
    @BindView(R.id.rbtn_ans_6_d)
    RadioButton rbtnAns6D;
    @BindView(R.id.rbtn_ans_7_a)
    RadioButton rbtnAns7A;
    @BindView(R.id.rbtn_ans_7_b)
    RadioButton rbtnAns7B;
    @BindView(R.id.rbtn_ans_7_c)
    RadioButton rbtnAns7C;
    @BindView(R.id.rbtn_ans_7_d)
    RadioButton rbtnAns7D;
    @BindView(R.id.rbtn_ans_8_a)
    RadioButton rbtnAns8A;
    @BindView(R.id.rbtn_ans_8_b)
    RadioButton rbtnAns8B;
    @BindView(R.id.rbtn_ans_8_c)
    RadioButton rbtnAns8C;
    @BindView(R.id.rbtn_ans_8_d)
    RadioButton rbtnAns8D;
    @BindView(R.id.tiet_ans_9)
    TextInputEditText tietAns9;
    @BindView(R.id.til_ans_9)
    TextInputLayout tilAns9;
    @BindView(R.id.tiet_ans_10)
    TextInputEditText tietAns10;
    @BindView(R.id.til_ans_10)
    TextInputLayout tilAns10;
    @BindView(R.id.btn_submit_ans)
    CustomButton btnSubmitCheckAns;
    @BindView(R.id.rg_que_1)
    RadioGroup rgQue1;
    @BindView(R.id.rg_que_2)
    RadioGroup rgQue2;
    @BindView(R.id.rg_que_3)
    RadioGroup rgQue3;
    @BindView(R.id.rg_que_5)
    RadioGroup rgQue5;
    @BindView(R.id.rg_que_6)
    RadioGroup rgQue6;
    @BindView(R.id.rg_que_7)
    RadioGroup rgQue7;
    @BindView(R.id.rg_que_8)
    RadioGroup rgQue8;
    @BindView(R.id.btn_check_ans)
    CustomButton btnCheckAns;
    @BindView(R.id.btn_reset_ans)
    CustomButton btnResetAns;
    //endregion

    //region Class variables
    private int score = 0;
    private int checkBoxScore = 0;
    private MyAlertDialog myAlertDialog;
    private boolean hasSubmitAlert = false;
    private boolean hasResultDialog = false;
    private boolean hasResetAlert = false;
    private boolean hasCheckAlert = false;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setValues(savedInstanceState);
    }

    /**
     * Saving state just before activity going to get destroyed and get re-created
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(AppKeys.INT_SCORE, getScore(getViewsToCheck()) + getScoreFromCheckBoxes(getCheckBoxesToCheck()));
        outState.putBoolean(AppKeys.BOOLEAN_SUBMIT_ALERT, hasSubmitAlert());
        outState.putBoolean(AppKeys.BOOLEAN_RESULT, hasResultDialog());
        outState.putBoolean(AppKeys.BOOLEAN_RESET_ALERT, hasResetAlert());
        outState.putBoolean(AppKeys.BOOLEAN_CHECK_ALERT, hasCheckAlert());
        gcDialog();
        super.onSaveInstanceState(outState);
    }

    /**
     * Gives mutable {@link #score}
     * <p>
     * Takes {@param views} to be checked. If given view is selectable, should have been checked (hence,
     * only those selectable and checkable views should be given to this method as in form of arguments which
     * should have been checked as right answer) and has checked indeed,
     * the method increases the value of {@link #score}. Thus, we care about radio buttons those should be checked
     * to get the point (increase the {@link #score} by 1) and not other radio buttons.
     * Also, if the given view is having user input type, i.e., edit field, it will check right answer
     * by comparision with the help of method {@link #getScoreFromInputField(View)}
     * <p>
     * Being used inside of {@link #onClickSubmit()}
     * Using {@link #getScoreFromInputField(View)} to check user input type views
     *
     * @param views to be checked.
     * @return mutable {@link #score}
     * @since 1.0
     */
    public int getScore(List<View> views) {
        score = 0;
        for (View view : views) {
            if (view instanceof RadioButton && ((RadioButton) view).isChecked()) {
                score++;
            } else if (view instanceof TextInputEditText) {
                getScoreFromInputField(view);
            }
        }
        return score;
    }

    /**
     * Gives all views those will be used to check user answer
     * <p>
     * Developer should include all views here those are useful to evaluate user's score.
     * If the view is radio button, we care about only right answer and not other.
     * If the view is check box (multiple answers), use {@link #getCheckBoxesToCheck()}
     * If the view is user input field, include it here.
     * <p>
     * Being used as arguments for {@link #getScore(List)}
     * Being used inside {@link #onClickSubmit()}
     *
     * @return An array of views
     * @since 1.0
     */
    public List<View> getViewsToCheck() {
        return Arrays.asList(rgQue1, rbtnAns1A, rgQue2, rbtnAns2C, rgQue3, rbtnAns3C,
                rgQue5, rbtnAns5B, rgQue6, rbtnAns6B, rgQue7, rbtnAns7A,
                rgQue8, rbtnAns8B, tietAns9, tietAns10);
    }

    /**
     * Gives mutable {@link #checkBoxScore}
     * <p>
     * Takes {@param checkBoxes} to be verified as an array argument.
     * If given view is selectable, should have been set checked (hence,
     * only those selectable and checkable views should be given to this method as in form of arguments which
     * should have been set checked as right answer) and has been set checked indeed,
     * the method increases the value of {@link #checkBoxScore}.
     * Thus, we care about checkBoxes those should have been set as checked
     * to get the point (increase the {@link #checkBoxScore} by 1) and not other check boxes.
     * <p>
     * Being used inside of {@link #onClickSubmit()}
     * Using {@link #hasAllChecked(CheckBox[])} to verify whether all given checkBoxes are set checked or not.
     *
     * @param checkBoxes to be verified.
     * @return mutable {@link #checkBoxScore}
     * @since 1.0
     */
    public int getScoreFromCheckBoxes(CheckBox[] checkBoxes) {
        checkBoxScore = 0;
        if (hasAllChecked(checkBoxes)) {
            checkBoxScore++;
        }
        return checkBoxScore;
    }

    /**
     * Gives all checkBoxes those will be verified to check user answer
     * <p>
     * Developer should include all checkBoxes here those are useful to evaluate user's score.
     * If the view is radio button or user input field, include it to {@link #getViewsToCheck()}
     * <p>
     * Being used as arguments for {@link #getScoreFromCheckBoxes(CheckBox[])}
     * Being used inside {@link #onClickSubmit()}
     *
     * @return An array of checkBox
     * @since 1.0
     */
    public CheckBox[] getCheckBoxesToCheck() {
        return new CheckBox[]{cbAns4A, cbAns4B, cbAns4C, cbAns4D};
    }

    /**
     * Gives the {@link #hasSubmitAlert} as boolean
     *
     * @return True if submitAlert dialog is currently visible {@link #showSubmitAlert()}
     * @since 1.0
     */
    public boolean hasSubmitAlert() {
        return hasSubmitAlert;
    }

    /**
     * Gives the {@link #hasResultDialog} as boolean
     *
     * @return True if result dialog is currently visible {@link #showScore(int)}
     * @since 1.0
     */
    public boolean hasResultDialog() {
        return hasResultDialog;
    }

    /**
     * Gives the {@link #hasResetAlert} as boolean
     *
     * @return True if reset alert dialog is currently visible
     * @since 1.0
     */
    public boolean hasResetAlert() {
        return hasResetAlert;
    }

    /**
     * Gives the {@link #hasCheckAlert} as boolean
     *
     * @return True if check alert dialog is currently visible
     * @since 1.0
     */
    public boolean hasCheckAlert() {
        return hasCheckAlert;
    }

    //Prevents memory leak for dialog. Garbage collection.
    public void gcDialog() {
        dismissDialog(myAlertDialog);
    }

    /**
     * Compares user input string (texts) to right answer and increases the mutable {@link #score} for each
     * right answer
     * <p>
     * Being used inside of {@link #onClickSubmit()}
     * Being used by {@link #getScore(List)}
     * <p>
     * Using {@link UtilMethods#checkNotNullEmpty(String)}
     * to check whether the user input is available or not for the comparision
     *
     * @param view User input field, e.g. EditText, TextInputEditText
     * @see UtilMethods#checkNotNullEmpty(String)
     * @since 1.0
     */
    private void getScoreFromInputField(View view) {
        switch (view.getId()) {
            case R.id.til_ans_9:
            case R.id.tiet_ans_9:
                if (UtilMethods.checkNotNullEmpty(tietAns9.getText())
                        && UtilMethods.checkNotNullEmpty(tietAns9.getText().toString())
                        && tietAns9.getText().toString().trim().equalsIgnoreCase(getString(R.string.str_adb))) {
                    score++;
                }
                break;

            case R.id.til_ans_10:
            case R.id.tiet_ans_10:
                if (UtilMethods.checkNotNullEmpty(tietAns10.getText())
                        && UtilMethods.checkNotNullEmpty(tietAns10.getText().toString())
                        && (tietAns10.getText().toString().trim().equalsIgnoreCase(getString(R.string.str_ddms))
                        || tietAns10.getText().toString().trim().equalsIgnoreCase(getString(R.string.str_ddms_dev)))) {
                    score++;
                }
                break;
        }
    }

    /**
     * A business logic to verify whether all given checkboxes have been set checked or not
     * Being used inside of {@link #getScoreFromCheckBoxes(CheckBox[])}
     *
     * @param checkBoxes to be verified whether they all have been set checked or not
     * @return true if all given checkBoxes have been set checked
     * @since 1.0
     */
    public boolean hasAllChecked(CheckBox[] checkBoxes) {
        List<CheckBox> cboxes = new ArrayList<>();
        for (CheckBox cb : checkBoxes) {
            if (cb.isChecked()) {
                cboxes.add(cb);
            }
        }
        return checkBoxes.length == cboxes.size();
    }

    /**
     * Dismisses given dialogFragment
     * */
    public void dismissDialog(DialogFragment dialogFragment) {
        if (dialogFragment != null && dialogFragment.getShowsDialog()) {
            dialogFragment.dismiss();
        }
    }

    /**
     * Sets the {@link #hasResetAlert} as boolean
     *
     * @since 1.0
     */
    public void setHasCheckAlert(boolean hasCheckAlert) {
        this.hasCheckAlert = hasCheckAlert;
    }

    /**
     * Sets value after re-creation of the activity
     * Being used in {@link #onCreate(Bundle)}
     *
     * @since 1.0
     */
    private void setValues(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(AppKeys.INT_SCORE);
            hasSubmitAlert = savedInstanceState.getBoolean(AppKeys.BOOLEAN_SUBMIT_ALERT);
            hasResultDialog = savedInstanceState.getBoolean(AppKeys.BOOLEAN_RESULT);
            hasResetAlert = savedInstanceState.getBoolean(AppKeys.BOOLEAN_RESET_ALERT);
            hasCheckAlert = savedInstanceState.getBoolean(AppKeys.BOOLEAN_CHECK_ALERT);
            if (hasSubmitAlert) {
                showSubmitAlert();
            }
            if (hasResultDialog) {
                showScore(score);
            }
            if (hasResetAlert) {
                showResetAlert();
            }
            if (hasCheckAlert) {
                showCheckAlert();
            }
        }
    }

    /**
     * Gets called when user presses {@code {@link #btnSubmitCheckAns}}
     * <p>
     * Shows {@link #showSubmitAlert()}
     * <p>
     *
     * @since 1.0
     */
    public void onClickSubmit() {
        showSubmitAlert();
    }

    /**
     * Shows an alert dialog before submitting the answers
     * Being used in {@link #onClickSubmit()}
     *
     * @see DialogFragment for more information
     * @since 1.0
     */
    public void showSubmitAlert() {
        gcDialog();
        setHasSubmitAlert(true);
        myAlertDialog = new MyAlertDialog()
                .setTitle(getString(R.string.label_alert))
                .setContentMsg(getString(R.string.label_are_you_sure_submit))
                .setBtnLeftText(getString(R.string.label_yes))
                .setBtnRightText(getString(R.string.label_no))
                .setTitleColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .setOnDialogBtnClick(new Callbacks.OnDialogBtnClick() {
                    @Override
                    public void onLeftBtnClick() {
                        showScore(0);
                        setHasSubmitAlert(false);
                    }

                    @Override
                    public void onRightBtnClick() {
                        dismissDialog(myAlertDialog);
                        setHasSubmitAlert(false);
                    }
                });
        myAlertDialog.show(getSupportFragmentManager(), AppConstantStrings.STR_DIALOG_SUBMIT_ALERT);
    }

    /**
     * Sets the {@link #hasSubmitAlert} boolean
     *
     * @param hasSubmitAlert True if submit alert dialog is currently visible {@link #showSubmitAlert()}
     * @since 1.0
     */
    public void setHasSubmitAlert(boolean hasSubmitAlert) {
        this.hasSubmitAlert = hasSubmitAlert;
    }

    /**
     * Shows score
     * Being used in {@link #showSubmitAlert()}
     *
     * @param score An int being used especially in {@link #setValues(Bundle)}
     * @see DialogFragment for more information
     * @since 1.0
     */
    public void showScore(int score) {
        gcDialog();
        setHasResultDialog(true);
        myAlertDialog = new MyAlertDialog()
                .setTitle(getString(R.string.label_result))
                .setContentMsg(getScoreMessage(score))
                .setBtnLeftText(getString(R.string.label_reset))
                .setBtnLeftColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .setBtnRightText(getString(R.string.label_dismiss))
                .setTitleColor(ContextCompat.getColor(this, android.R.color.black))
                .setOnDialogBtnClick(new Callbacks.OnDialogBtnClick() {
                    @Override
                    public void onLeftBtnClick() {
                        showResetAlert();
                        setHasResultDialog(false);
                    }

                    @Override
                    public void onRightBtnClick() {
                        dismissDialog(myAlertDialog);
                        setHasResultDialog(false);
                    }
                });
        myAlertDialog.show(getSupportFragmentManager(), AppConstantStrings.STR_DIALOG_RESULT);
    }

    /**
     * Sets the {@link #hasResultDialog} boolean
     *
     * @param hasResultDialog True if result alert dialog is currently visible {@link #showScore(int)}
     * @since 1.0
     */
    public void setHasResultDialog(boolean hasResultDialog) {
        this.hasResultDialog = hasResultDialog;
    }

    /**
     * Gives the score based on correct answers given by the end user.
     * Being used in {@link #showScore(int)}
     * <p>
     * Shows total of {@code {@link #score}} & {@code {@link #checkBoxScore}} achieved by user
     * </P>
     *
     * @param score An int that represents score to display especially for {@link #setValues(Bundle)}
     * @see DialogFragment for more information
     * @since 1.0
     */
    public String getScoreMessage(int score) {
        return getString(R.string.msg_score)
                .replace("#",
                        score == 0 ? String.valueOf(
                                getScore(getViewsToCheck()) + getScoreFromCheckBoxes(getCheckBoxesToCheck()))
                                : String.valueOf(score));
    }

    /**
     * Shows an alert dialog before resetting the quiz.
     * Being used in {@link #showScore(int)}
     *
     * @see DialogFragment for more information
     * @since 1.0
     */
    public void showResetAlert() {
        gcDialog();
        setHasResetAlert(true);
        myAlertDialog = new MyAlertDialog()
                .setTitle(getString(R.string.label_alert))
                .setContentMsg(getString(R.string.label_are_you_sure_reset))
                .setBtnLeftText(getString(R.string.label_reset))
                .setBtnLeftColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .setBtnRightText(getString(R.string.label_no))
                .setTitleColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .setOnDialogBtnClick(new Callbacks.OnDialogBtnClick() {
                    @Override
                    public void onLeftBtnClick() {
                        resetQuiz();
                        setHasResetAlert(false);
                    }

                    @Override
                    public void onRightBtnClick() {
                        dismissDialog(myAlertDialog);
                        setHasResetAlert(false);
                    }
                });
        myAlertDialog.show(getSupportFragmentManager(), AppConstantStrings.STR_DIALOG_RESET_ALERT);
    }

    /**
     * Shows check alert dialog before revealing answers.
     * Being used in {@link #onViewClicked(View)} and {@link #setValues(Bundle)}
     *
     * @see DialogFragment for more information
     * @since 1.0
     */
    public void showCheckAlert() {
        gcDialog();
        setHasCheckAlert(true);
        myAlertDialog = new MyAlertDialog()
                .setTitle(getString(R.string.label_alert))
                .setContentMsg(getString(R.string.label_are_you_sure_check))
                .setBtnLeftText(getString(R.string.label_yes))
                .setBtnLeftColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .setBtnRightText(getString(R.string.label_no))
                .setTitleColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .setOnDialogBtnClick(new Callbacks.OnDialogBtnClick() {
                    @Override
                    public void onLeftBtnClick() {
                        setAnswers();
                        dismissDialog(myAlertDialog);
                        setHasCheckAlert(false);
                    }

                    @Override
                    public void onRightBtnClick() {
                        dismissDialog(myAlertDialog);
                        setHasCheckAlert(false);
                    }
                });
        myAlertDialog.show(getSupportFragmentManager(), AppConstantStrings.STR_DIALOG_CHECK_ALERT);
    }

    /**
     * Resets all user inputs, clears all given answers
     * <p>
     * Also resets {@link #score} and {@link #checkBoxScore} to zero (0).
     * <p>
     * Being used by (in) {@link #onClickSubmit()}
     *
     * @since 1.0
     */
    public void resetQuiz() {
        for (View view : getViewsToCheck()) {
            //Resets the radio buttons
            if (view instanceof RadioGroup) {
                ((RadioGroup) view).clearCheck();
                //Clears the text input fields
            } else if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setText("");
            }
        }
        //Uncheck checkboxes
        for (CheckBox checkBox : getCheckBoxesToCheck()) {
            checkBox.setChecked(false);
        }
        //Resets score values
        score = 0;
        checkBoxScore = 0;
        //Enable submit button
        btnSubmitCheckAns.setEnabled(true);
    }

    /**
     * Sets the {@link #hasResetAlert} boolean
     *
     * @param hasResetAlert True if reset alert dialog is currently visible {@link #showResetAlert()}
     * @since 1.0
     */
    public void setHasResetAlert(boolean hasResetAlert) {
        this.hasResetAlert = hasResetAlert;
    }

    @OnClick({R.id.btn_submit_ans, R.id.btn_check_ans, R.id.btn_reset_ans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit_ans:
                onClickSubmit();
                break;
            case R.id.btn_check_ans:
                showCheckAlert();
                break;
            case R.id.btn_reset_ans:
                showResetAlert();
                break;
        }
    }

    /**
     * Sets answers so that end user can see actual answers
     * Being used in {@link #onViewClicked(View)}
     *
     * @since 1.0
     */
    private void setAnswers() {
        List<View> views = getViewsToCheck();
        for (View view : views) {
            if (view instanceof RadioButton) {
                ((RadioButton) view).setChecked(true);
            } else if (view instanceof TextInputEditText) {
                switch (view.getId()) {
                    case R.id.tiet_ans_9:
                        tietAns9.setText(getString(R.string.str_adb));
                        break;
                    case R.id.tiet_ans_10:
                        tietAns10.setText(getString(R.string.str_ddms_dev));
                        break;
                }
            }
        }
        CheckBox[] checkBoxes = getCheckBoxesToCheck();
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setChecked(true);
        }
        score = 0;
        checkBoxScore = 0;
        btnSubmitCheckAns.setEnabled(false);
    }
}
