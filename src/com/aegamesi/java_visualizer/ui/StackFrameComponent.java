package com.aegamesi.java_visualizer.ui;

import com.aegamesi.java_visualizer.model.Frame;
import com.aegamesi.java_visualizer.model.Value;
import com.intellij.util.ui.JBUI;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class StackFrameComponent extends JPanel {
	private Frame frame;
	private VisualizationPanel viz;

	StackFrameComponent(VisualizationPanel viz, Frame frame, boolean first) {
		this.frame = frame;
		this.viz = viz;
		setBackground(first ? Constants.colorFrameBGFirst : Constants.colorFrameBG);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel labelName = new JLabel(frame.name, JLabel.LEFT);
		labelName.setFont(Constants.fontUIMono);
		labelName.setBorder(JBUI.Borders.empty(4));
		labelName.setAlignmentX(RIGHT_ALIGNMENT);
		add(labelName);

		List<JComponent> keys = new ArrayList<>();
		List<JComponent> vals = new ArrayList<>();
		for (Map.Entry<String, Value> local : frame.locals.entrySet()) {
			JLabel localLabel = new JLabel(local.getKey(), JLabel.RIGHT);
			ValueComponent value = new ValueComponent(viz, local.getValue(), first);
			value.setBorder(JBUI.Borders.customLine(Constants.colorFrameOutline, 0, 1, 1, 0));
			keys.add(localLabel);
			vals.add(value);
		}
		KVComponent locals = new KVComponent();
		locals.setPadding(4);
		locals.setComponents(keys, vals);
		locals.build();
		locals.setAlignmentX(RIGHT_ALIGNMENT);
		locals.setMaximumSize(locals.getPreferredSize());
		add(locals);
	}
}
