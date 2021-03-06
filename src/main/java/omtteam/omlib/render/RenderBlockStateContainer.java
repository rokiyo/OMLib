package omtteam.omlib.render;

import net.minecraft.block.state.IBlockState;

/**
 * Created by Keridos on 29/01/17.
 * This Class
 */
public class RenderBlockStateContainer {
    private IBlockState renderState;

    public RenderBlockStateContainer(IBlockState state){
        renderState = state;
    }

    public IBlockState getRenderState() {
        return renderState;
    }

    public void setRenderState(IBlockState renderState) {
        this.renderState = renderState;
    }

    @Override
    public String toString(){
        return renderState.toString();
    }
}
