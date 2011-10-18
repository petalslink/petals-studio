package com.ebmwebsourcing.petals.services.jbi.wizard;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;

import com.ebmwebsourcing.petals.services.PetalsColors;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentCategory;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentSupportExtensionDesc;

public class ComponentSupportLabelProvider extends LabelProvider implements IStyledLabelProvider {

	@Override
	public String getText(Object item) {
		return this.getStyledText(item).getString();
	}

	public ComponentSupportLabelProvider() {
		//FontData fd = JFaceResources.getDefaultFontDescriptor().getFontData();
		//Font defaultFont = new Font(Display.getDefault(), fd)
		//Color descColor = getDarkP
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public StyledString getStyledText(Object element) {
		StyledString res = new StyledString();
		if (element instanceof ComponentSupportExtensionDesc) {
			ComponentSupportExtensionDesc desc = (ComponentSupportExtensionDesc)element;
			res.append(desc.getLabel(), new Styler() {
				@Override
				public void applyStyles(TextStyle textStyle) {
					textStyle.foreground = PetalsColors.getDarkPurple();
				}
			});
			/*res.append(desc.getId(), new Styler() {
				@Override
				public void applyStyles(TextStyle textStyle) {
					textStyle.foreground = PetalsColors.getOrange();
				}
			});*/
		} else if (element instanceof ComponentCategory) {
			ComponentCategory category = (ComponentCategory)element;
			res.append(category.getLabel(), new Styler() {
				@Override
				public void applyStyles(TextStyle textStyle) {
					// TODO put label in bold
					textStyle.foreground = PetalsColors.getDarkPurple();
				}
			});
			/*res.append(category.getDescription(), new Styler() {
				@Override
				public void applyStyles(TextStyle textStyle) {
					textStyle.foreground = PetalsColors.getOrange();
				}
			});*/
		}
		return res;
	}

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
}
