package com.quetzal;

import java.awt.*;
import javax.inject.Inject;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.components.TextComponent;

public class WhistleOverlay extends WidgetItemOverlay {
    @Inject
    WhistleOverlay() {
        showOnInventory();
        showOnBank();
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
        var whistle = WhistleItem.forItemID(itemId);
        if (whistle == null) return;

        graphics.setFont(FontManager.getRunescapeSmallFont());

        var bounds = widgetItem.getCanvasBounds();

        renderCharges(whistle, graphics, bounds);
        renderTarget(whistle, graphics, bounds);
    }

    private void renderCharges(WhistleItem whistle, Graphics2D graphics, Rectangle bounds) {
        final TextComponent textComponent = new TextComponent();
        textComponent.setPosition(new Point(bounds.x - 1, bounds.y + 10));

        if (whistle.charges >= 0) {
            if (whistle.charges == 0) {
                textComponent.setColor(Color.RED);
            } else if (whistle.charges == whistle.capacity) {
                textComponent.setColor(Color.GREEN);
            } else {
                textComponent.setColor(Color.YELLOW);
            }

            textComponent.setText(String.valueOf(whistle.charges));
        }
        else {
            textComponent.setColor(Color.ORANGE);
            textComponent.setText("?");
        }
        textComponent.render(graphics);
    }

    private void renderTarget(WhistleItem whistle, Graphics2D graphics, Rectangle bounds) {
        final TextComponent textComponent = new TextComponent();
        textComponent.setPosition(new Point(bounds.x - 1, bounds.y + bounds.height));

        if (whistle.target.equals("?")) {
            textComponent.setColor(Color.ORANGE);
        } else {
            textComponent.setColor(Color.YELLOW);
        }

        textComponent.setText(whistle.target);
        textComponent.render(graphics);
    }
}
