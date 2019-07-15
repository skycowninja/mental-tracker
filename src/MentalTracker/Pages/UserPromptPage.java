package MentalTracker.Pages;

import MentalTracker.DataPortions.Prompts.MentalPrompt;
import MentalTracker.GuiComponents.PromptComponentsGenerator;
import MentalTracker.GuiComponents.ComponantName;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import static com.codename1.ui.util.Resources.getGlobalResources;

public class UserPromptPage extends Form{
    private Form _Page;
    private MentalPrompt _Prompt;
    private PromptComponentsGenerator _Components;

    public UserPromptPage(MentalPrompt prompt)
    {
        this (getGlobalResources(), prompt);
    }

    public UserPromptPage (Resources resourceObjectInstance, MentalPrompt prompt)
    {
        _Prompt = prompt;
        _Components = new PromptComponentsGenerator(_Prompt);
        initGuiBuilderComponents (resourceObjectInstance);
    }

    private void onSliderActionEvent(ActionEvent ev) {

    }

    private void onSliderDataChangeEvent(Component cmp, int type, int index) {

    }

    private void onNextButtonActionEvent (ActionEvent ev) {

    }

    private void onBackButtonActionEvent (ActionEvent ev) {

    }

    private void onButtonNoActionEvent (ActionEvent ev) {

    }

    private void onButtonYesActionEvent (ActionEvent ev) {

    }


    private void guiBuilderBindComponentListeners()
    {
        EventCallbackClass callback = new EventCallbackClass();

        _Components.BackButton.addActionListener(callback);
        _Components.AdvanceButton.addActionListener(callback);

        if (_Components.CheckInputButtons())
        {
            _Components.InputButtonNo.addActionListener(callback);
            _Components.InputButtonYes.addActionListener(callback);
        }
        if (_Components.CheckSlider())
        {
            _Components.InputSlider.addActionListener(callback);
            _Components.InputSlider.addDataChangedListener(callback);
        }

    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;

        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();

            if(sourceComponent.getParent().getLeadParent() != null && (sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.MultiButton || sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.SpanButton)) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == _Components.InputSlider) {
                onSliderActionEvent(ev);
            }
            if(sourceComponent == _Components.AdvanceButton) {
                onNextButtonActionEvent(ev);
            }
            if(sourceComponent == _Components.BackButton) {
                onBackButtonActionEvent(ev);
            }
            if(sourceComponent == _Components.InputButtonNo) {
                onButtonNoActionEvent (ev);
            }
            if(sourceComponent == _Components.InputButtonNo) {
                onButtonYesActionEvent (ex);
            }
        }

        public void dataChanged(int type, int index) {
            onSliderDataChangeEvent(cmp, type, index);
        }
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {

        guiBuilderBindComponentListeners();
        setLayout(new LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(false);
        setInlineStylesTheme(resourceObjectInstance);
        setInlineAllStyles("bgColor:efefef;");
        setTitle("Track Your Mind");
        setName("UserPromptPage");

        for (ComponantName component: _Components.EnabledComponents)
        {
            switch (component) {
                case TOP_INSET:
                    addComponent(_Components.TopInset);
                    _Components.TopInset.setPreferredSizeStr("inherit 4.2328043mm");
                    _Components.TopInset.setInlineStylesTheme(resourceObjectInstance);
                    _Components.TopInset.setName("topInset");
                    ((LayeredLayout)_Components.TopInset.getParent().getLayout()).
                            setInsets(_Components.TopInset, "1.6mm 5.0mm auto 5.0mm").
                            setReferenceComponents(_Components.TopInset, "-1 -1 -1 -1").
                            setReferencePositions(_Components.TopInset, "0.0 0.0 0.0 0.0");
                    break;

                case ADV_BUTTON:
                    addComponent(_Components.AdvanceButton);
                    _Components.AdvanceButton.setPreferredSizeStr("116.93122mm 23.544973mm");
                    _Components.AdvanceButton.setInlineStylesTheme(resourceObjectInstance);
                    _Components.AdvanceButton.setName("confirmButton");
                    _Components.AdvanceButton.setIcon(resourceObjectInstance.getImage("continueButton.png"));
                    _Components.AdvanceButton.setPressedIcon(resourceObjectInstance.getImage("continuePress.png"));
                    ((LayeredLayout)_Components.AdvanceButton.getParent().getLayout()).
                            setInsets(_Components.AdvanceButton, "auto -125% 0 auto");
                    break;

                case BACK_BUTTON:
                    addComponent(_Components.BackButton);
                    _Components.BackButton.setPreferredSizeStr("20.042328mm 10.8465605mm");
                    _Components.BackButton.setInlineStylesTheme(resourceObjectInstance);
                    _Components.BackButton.setName("backButton");
                    _Components.BackButton.setIcon(resourceObjectInstance.getImage("backButton.png"));
                    _Components.BackButton.setPressedIcon(resourceObjectInstance.getImage("backPressed.png"));
                    ((LayeredLayout)_Components.BackButton.getParent().getLayout()).
                            setInsets(_Components.BackButton, "0.5mm auto auto 0.0mm").
                            setReferenceComponents(_Components.BackButton, "0 -1 -1 0 ").
                            setReferencePositions(_Components.BackButton, "-1 0.0 0.0 0.0");
                    break;

                case INPUT_SLIDER:
                    addComponent(_Components.InputSlider);
                    _Components.InputSlider.setPreferredSizeStr("88.88889mm 11.111112mm");
                    _Components.InputSlider.setEditable(true);
                    _Components.InputSlider.setFocusable(true);
                    _Components.InputSlider.setScrollVisible(true);
                    _Components.InputSlider.setInlineStylesTheme(resourceObjectInstance);
                    _Components.InputSlider.setInlineAllStyles("alignment:center;");
                    _Components.InputSlider.setName("Slider");
                    _Components.InputSlider.setGap(2);
                    _Components.InputSlider.setIncrements(1);
                    _Components.InputSlider.setProgress(2);
                    _Components.InputSlider.setRenderValueOnTop(true);
                    ((LayeredLayout)_Components.InputSlider.getParent().getLayout()).
                            setInsets(_Components.InputSlider, "0 20% auto 20%").
                            setReferenceComponentTop(_Components.InputSlider, _Components.BackButton, 5f);
                    break;

                case PROMPT_TITLE:
                    addComponent(_Components.PromptTitle);
                    _Components.PromptTitle.setPreferredSizeStr("126.455025mm 24.338625mm");
                    _Components.PromptTitle.setInlineStylesTheme(resourceObjectInstance);
                    _Components.PromptTitle.setInlineAllStyles("alignment:center;");
                    _Components.PromptTitle.setName("Label");
                    _Components.PromptTitle.setIcon(resourceObjectInstance.getImage("moodLabel.png"));
                    ((LayeredLayout)_Components.PromptTitle.getParent().getLayout()).
                            setInsets(_Components.PromptTitle, "0% auto auto 0").
                            setReferenceComponentTop(_Components.PromptTitle, _Components.BackButton, 7f);
                    break;

                case INPUT_BUTTON_NO:
                    addComponent(_Components.InputButtonNo);
                    _Components.InputButtonNo.setPreferredSizeStr("18.78307mm 6.6137567mm");
                    _Components.InputButtonNo.setSelected(false);
                    _Components.InputButtonNo.setUIID("ToggleButton");
                    _Components.InputButtonNo.setInlineStylesTheme(resourceObjectInstance);
                    _Components.InputButtonNo.setInlineAllStyles("border:none; bgColor:efefef;");
                    _Components.InputButtonNo.setInlineSelectedStyles("border:none;");
                    _Components.InputButtonNo.setInlinePressedStyles("border:none;");
                    _Components.InputButtonNo.setGroup("ButtonGroup");
                    _Components.InputButtonNo.setName("ButtonNo");
                    _Components.InputButtonNo.setIcon(resourceObjectInstance.getImage("noButtonUnsele.png"));
                    _Components.InputButtonNo.setPressedIcon(resourceObjectInstance.getImage("noButtonSelect.png"));
                    ((LayeredLayout)_Components.InputButtonNo.getParent().getLayout()).
                            setInsets(_Components.InputButtonNo, "0 10% auto 10%").
                            setReferenceComponentTop(_Components.InputButtonNo, _Components.BackButton, 5f);
                    break;

                case INPUT_BUTTON_YES:
                    addComponent(_Components.InputButtonYes);
                    _Components.InputButtonYes.setPreferredSizeStr("21.16402mm 7.142857mm");
                    _Components.InputButtonYes.setSelected(false);
                    _Components.InputButtonYes.setUIID("ToggleButton");
                    _Components.InputButtonYes.setInlineStylesTheme(resourceObjectInstance);
                    _Components.InputButtonYes.setInlineAllStyles("border:none; bgColor:efefef;");
                    _Components.InputButtonYes.setInlineSelectedStyles("border:none;");
                    _Components.InputButtonYes.setInlinePressedStyles("border:none;");
                    _Components.InputButtonYes.setGroup("ButtonGroup");
                    _Components.InputButtonYes.setName("ButtonYes");
                    _Components.InputButtonYes.setIcon(resourceObjectInstance.getImage("yesButtonUnselect.png"));
                    _Components.InputButtonYes.setPressedIcon(resourceObjectInstance.getImage("yesButtonSelect.png"));
                    ((LayeredLayout)_Components.InputButtonYes.getParent().getLayout()).
                            setInsets(_Components.InputButtonYes, "0 10% auto 70%").
                            setReferenceComponentTop(_Components.InputButtonYes, _Components.BackButton, 5f);
                    break;

                case PROMPT_QUESTION:
                    addComponent(_Components.PromptQuestion);
                    _Components.PromptQuestion.setName("Prompt");
                    ((LayeredLayout) _Components.PromptQuestion.getParent().getLayout()).
                            setInsets(_Components.PromptQuestion, "0 auto auto auto").
                            setReferenceComponentTop(_Components.PromptQuestion, _Components.BackButton, 3f);
                    break;

                case INPUT_TEXT_AREA:
                    addComponent(_Components.InputTextArea);
                    _Components.InputTextArea.setEditable(true);
                    _Components.InputTextArea.setSingleLineTextArea(true);
                    _Components.InputTextArea.setHint("Hours");
                    _Components.InputTextArea.setInlineStylesTheme(resourceObjectInstance);
                    _Components.InputTextArea.getStyle().setAlignment(CENTER);
                    _Components.InputTextArea.setVerticalAlignment(CENTER);
                    _Components.InputTextArea.setName("sleepInput");
                    ((LayeredLayout)_Components.InputTextArea.getParent().getLayout()).
                            setInsets(_Components.InputTextArea, "0 30% auto 33%").
                            setReferenceComponentTop(_Components.InputTextArea, _Components.BackButton, 5f);
                    break;
            }
        }
    }

}