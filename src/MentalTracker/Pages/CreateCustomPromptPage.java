package MentalTracker.Pages;

import MentalTracker.DataPortions.Prompts.MentalPrompt;
import MentalTracker.DataPortions.Prompts.MentalPrompts;
import MentalTracker.DataPortions.Prompts.PromptDataType;
import MentalTracker.GuiComponents.CreatePromptComponGenerator;
import MentalTracker.MentalExceptions.SaveFileException;
import com.codename1.io.Log;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;


import static com.codename1.ui.util.Resources.getGlobalResources;

public class CreateCustomPromptPage extends Form {

    private Form _Previous;
    private MentalPrompt _NewPrompt;
    private MentalPrompts _AllPrompts;
    private CreatePromptComponGenerator _Components;

    private String _Name;
    private String _Question;
    private PromptDataType _AnswerType;
    private int _Min;
    private int _Max;

    private String _Error = "";

    public CreateCustomPromptPage(Form previous, MentalPrompts prompts)
    {
        this (getGlobalResources());
        _Previous = previous;
        _AllPrompts = prompts;
    }

    public CreateCustomPromptPage (Resources resourcesObjectInstance)
    {
        _Components = new CreatePromptComponGenerator();
        initGuiBuilderComponents (resourcesObjectInstance);
    }

    private void onMaxInputActionEvent(ActionEvent ev) {
        try {
            _Max = Integer.parseInt(_Components.InputMaxTextArea.getText());
        } catch (NumberFormatException e) {
            Log.e(e.getCause());
        }
    }

    private void onMinInputActionEvent(ActionEvent ev) {
        try {
            _Min = Integer.parseInt(_Components.InputMinTextArea.getText());
        } catch (NumberFormatException e) {
            Log.e(e.getCause());
        }
    }

    private void onResponseInputActionEvent(ActionEvent ev) {
        switch (_Components.InputResponseTextArea.getText())
        {
            case "slider": _AnswerType = PromptDataType.INT; break;
            case "yes/no": _AnswerType = PromptDataType.BOOL; break;
            case "number": _AnswerType = PromptDataType.DOUBLE; break;
            case "words": _AnswerType = PromptDataType.STRING; break;
            default : break;
        }
    }

    private void onCreateButtonActionEvent(ActionEvent ev) {
        try {
            _NewPrompt = new MentalPrompt(_Name, _Question, _AnswerType );
            if ((_AnswerType == PromptDataType.INT) || (_AnswerType == PromptDataType.DOUBLE))
            {
                if (_Max > 0) _NewPrompt.SetMinMax(_Min, _Max);
                else if (_AnswerType == PromptDataType.INT) _Error += "Max must be more than 0";
            }
        } catch (Exception e) {
            Log.e(e.getCause());
        }

        if (_NewPrompt == null) return;
        _AllPrompts.add(_NewPrompt);

        try {
            _AllPrompts.SavePrompts(null);
        } catch (SaveFileException e) {
            Log.e(e.getCause());
        }
    }

    private void onBackButtonActionEvent(ActionEvent ev) {
        _Previous.show();
    }

    private void onPromptEnteredActionEvent(ActionEvent ev) {
        _Question = _Components.InputPromptTextArea.getText();
    }

    private void onNameEnteredEvent(ActionEvent ev) {
        _Name = _Components.InputNameTextArea.getText();
    }


    private void guiBuilderBindComponentListeners()
    {
        EventCallbackClass callback = new EventCallbackClass();

        _Components.BackButton.addActionListener(callback);
        _Components.CreateButton.addActionListener(callback);
        _Components.InputNameTextArea.addActionListener(callback);
        _Components.InputPromptTextArea.addActionListener(callback);
        _Components.InputResponseTextArea.addActionListener(callback);
        _Components.InputMinTextArea.addActionListener(callback);
        _Components.InputMaxTextArea.addActionListener(callback);
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

            if(sourceComponent == _Components.InputNameTextArea) {
                onNameEnteredEvent(ev);
            }
            if(sourceComponent == _Components.InputPromptTextArea) {
                onPromptEnteredActionEvent(ev);
            }
            if(sourceComponent == _Components.BackButton) {
                onBackButtonActionEvent(ev);
            }
            if(sourceComponent == _Components.CreateButton) {
                onCreateButtonActionEvent(ev);
            }
            if(sourceComponent == _Components.InputResponseTextArea) {
                onResponseInputActionEvent (ev);
            }
            if(sourceComponent == _Components.InputMinTextArea) {
                onMinInputActionEvent (ev);
            }
            if(sourceComponent == _Components.InputMaxTextArea) {
                onMaxInputActionEvent (ev);
            }
        }

        public void dataChanged(int type, int index) {
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
        setName("CreatePromptPage");

        addComponent(_Components.TopInsert);
        _Components.TopInsert.setPreferredSizeStr("inherit 4.2328043mm");
        _Components.TopInsert.setInlineStylesTheme(resourceObjectInstance);
        _Components.TopInsert.setName("topInset");
        ((LayeredLayout)_Components.TopInsert.getParent().getLayout()).
                setInsets(_Components.TopInsert, "1.6mm 5.0mm auto 5.0mm").
                setReferenceComponents(_Components.TopInsert, "-1 -1 -1 -1").
                setReferencePositions(_Components.TopInsert, "0.0 0.0 0.0 0.0");

        addComponent(_Components.CreateButton);
        _Components.CreateButton.setPreferredSizeStr("116.93122mm 23.544973mm");
        _Components.CreateButton.setInlineStylesTheme(resourceObjectInstance);
        _Components.CreateButton.setName("confirmButton");
        _Components.CreateButton.setIcon(resourceObjectInstance.getImage("continueButton.png"));
        _Components.CreateButton.setPressedIcon(resourceObjectInstance.getImage("continuePress.png"));
        ((LayeredLayout)_Components.CreateButton.getParent().getLayout()).
                setInsets(_Components.CreateButton, "auto -125% 0 auto");

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

        addComponent(_Components.Prompt);
        _Components.Prompt.setName("Prompt");
        ((LayeredLayout) _Components.Prompt.getParent().getLayout()).
                setInsets(_Components.Prompt, "0 auto auto auto").
                setReferenceComponentTop(_Components.Prompt, _Components.BackButton, 2f);

        addComponent(_Components.InputNameTextArea);
        _Components.InputNameTextArea.setEditable(true);
        _Components.InputNameTextArea.setSingleLineTextArea(true);
        _Components.InputNameTextArea.setInlineStylesTheme(resourceObjectInstance);
        _Components.InputNameTextArea.getStyle().setAlignment(CENTER);
        _Components.InputNameTextArea.setName("PromptName");
        ((LayeredLayout)_Components.InputNameTextArea.getParent().getLayout()).
                setInsets(_Components.InputNameTextArea, "0 10% auto 5%").
                setReferenceComponentTop(_Components.InputNameTextArea, _Components.BackButton, 3f);

        addComponent(_Components.InputPromptTextArea);
        _Components.InputPromptTextArea.setEditable(true);
        _Components.InputPromptTextArea.setSingleLineTextArea(true);
        _Components.InputPromptTextArea.setInlineStylesTheme(resourceObjectInstance);
        _Components.InputPromptTextArea.getStyle().setAlignment(CENTER);
        _Components.InputPromptTextArea.setName("PromptQuestion");
        ((LayeredLayout)_Components.InputPromptTextArea.getParent().getLayout()).
                setInsets(_Components.InputPromptTextArea, "0 10% auto 5%").
                setReferenceComponentTop(_Components.InputPromptTextArea, _Components.BackButton, 4f);

        addComponent(_Components.InputResponseTextArea);
        _Components.InputResponseTextArea.setEditable(true);
        _Components.InputResponseTextArea.setSingleLineTextArea(true);
        _Components.InputResponseTextArea.setInlineStylesTheme(resourceObjectInstance);
        _Components.InputResponseTextArea.getStyle().setAlignment(CENTER);
        _Components.InputResponseTextArea.setName("PromptResponse");
        ((LayeredLayout)_Components.InputResponseTextArea.getParent().getLayout()).
                setInsets(_Components.InputResponseTextArea, "0 10% auto 5%").
                setReferenceComponentTop(_Components.InputResponseTextArea, _Components.BackButton, 5f);

        addComponent(_Components.InputMinTextArea);
        _Components.InputMinTextArea.setEditable(true);
        _Components.InputMinTextArea.setSingleLineTextArea(true);
        _Components.InputMinTextArea.setInlineStylesTheme(resourceObjectInstance);
        _Components.InputMinTextArea.getStyle().setAlignment(CENTER);
        _Components.InputMinTextArea.setName("PromptMin");
        ((LayeredLayout)_Components.InputMinTextArea.getParent().getLayout()).
                setInsets(_Components.InputMinTextArea, "0 10% auto 5%").
                setReferenceComponentTop(_Components.InputMinTextArea, _Components.BackButton, 6f);

        addComponent(_Components.InputMaxTextArea);
        _Components.InputMaxTextArea.setEditable(true);
        _Components.InputMaxTextArea.setSingleLineTextArea(true);
        _Components.InputMaxTextArea.setInlineStylesTheme(resourceObjectInstance);
        _Components.InputMaxTextArea.getStyle().setAlignment(CENTER);
        _Components.InputMaxTextArea.setName("PromptMin");
        ((LayeredLayout)_Components.InputMaxTextArea.getParent().getLayout()).
                setInsets(_Components.InputMaxTextArea, "0 10% auto 5%").
                setReferenceComponentTop(_Components.InputMaxTextArea, _Components.BackButton, 7f);

    }
}
