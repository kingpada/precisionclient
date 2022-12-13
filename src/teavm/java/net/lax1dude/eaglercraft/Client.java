package net.lax1dude.eaglercraft;

import net.lax1dude.eaglercraft.adapter.DetectAnisotropicGlitch;
import net.lax1dude.eaglercraft.adapter.EaglerAdapterImpl2;
import net.minecraft.client.Minecraft;
import net.minecraft.src.network.ServerList;
import org.json.JSONException;
import org.json.JSONObject;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSExceptions;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;
import org.teavm.jso.browser.Window;
import org.teavm.jso.core.JSError;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.webgl.WebGLRenderingContext;

import static org.teavm.jso.webgl.WebGLRenderingContext.*;

public class Client {
    private static final String crashImage = "data:image/png;base64,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c+Jxky5I/Mh1xeM3zkWXBZ4pmpnUPLFILBU7WOlgVjI14mniiKrplC9kPVY5b3HWKjXWuid/YSivr6S5TmsEcSwhgSQkKKihjApsRGnXSbGQovOYj3/Y9SfJpZCrDEaOBVShQXb94H/we7ZWYWrSSwrFgO4Xx/kYBXp2gWbdcb6PHad5AgSfgSu97a82gNlP0uttLXIEDGwDF9dtTdkDLneA8JMhm7IrBWkJhQLwfkbflAMGb4G+NW9urXOcPgAZmtXyDXBwCIwVKXvd5929nXP7t6c1vx8743KRRjbQVgAADfdpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+Cjx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDQuNC4wLUV4aXYyIj4KIDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+CiAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIgogICAgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIKICAgIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIKICAgIHhtbG5zOkdJTVA9Imh0dHA6Ly93d3cuZ2ltcC5vcmcveG1wLyIKICAgIHhtbG5zOnRpZmY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vdGlmZi8xLjAvIgogICAgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIgogICB4bXBNTTpEb2N1bWVudElEPSJnaW1wOmRvY2lkOmdpbXA6NDJlMTU3MGEtNmMyZS00Y2E1LWI3ZTMtOGI4ODI1MmMwZDMwIgogICB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjU1NGY3N2UwLTc4NmEtNGFlZS1iYjhmLWNhYTBiZGNiYzE3MSIKICAgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSJ4bXAuZGlkOmNmMWYyMjUxLWIwY2QtNDE1NS1hMjAyLTExNGI0ZGM2MmFhNSIKICAgZGM6Rm9ybWF0PSJpbWFnZS9wbmciCiAgIEdJTVA6QVBJPSIyLjAiCiAgIEdJTVA6UGxhdGZvcm09IldpbmRvd3MiCiAgIEdJTVA6VGltZVN0YW1wPSIxNjQzMDYxODUwNDk0OTc0IgogICBHSU1QOlZlcnNpb249IjIuMTAuMjQiCiAgIHRpZmY6T3JpZW50YXRpb249IjEiCiAgIHhtcDpDcmVhdG9yVG9vbD0iR0lNUCAyLjEwIj4KICAgPHhtcE1NOkhpc3Rvcnk+CiAgICA8cmRmOlNlcT4KICAgICA8cmRmOmxpCiAgICAgIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiCiAgICAgIHN0RXZ0OmNoYW5nZWQ9Ii8iCiAgICAgIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6ODUyMGQ4YTMtMWRhZC00ZjIwLWFjOTktODg4OTJkZDExNDQ0IgogICAgICBzdEV2dDpzb2Z0d2FyZUFnZW50PSJHaW1wIDIuMTAgKFdpbmRvd3MpIgogICAgICBzdEV2dDp3aGVuPSIyMDIxLTEyLTE3VDE3OjIyOjQ4Ii8+CiAgICAgPHJkZjpsaQogICAgICBzdEV2dDphY3Rpb249InNhdmVkIgogICAgICBzdEV2dDpjaGFuZ2VkPSIvIgogICAgICBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjJkY2U5N2M4LTBkZjItNGQzNi1iMzE1LWE0YjdmMmUyMjJiNSIKICAgICAgc3RFdnQ6c29mdHdhcmVBZ2VudD0iR2ltcCAyLjEwIChXaW5kb3dzKSIKICAgICAgc3RFdnQ6d2hlbj0iMjAyMi0wMS0yNFQxNDowNDoxMCIvPgogICAgPC9yZGY6U2VxPgogICA8L3htcE1NOkhpc3Rvcnk+CiAgPC9yZGY6RGVzY3JpcHRpb24+CiA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgCjw/eHBhY2tldCBlbmQ9InciPz61xwk6AAAABmJLR0QAnQCdAJ2roJyEAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH5gEYFgQKOBb3JwAAIABJREFUeNrtvXl0lFWePv7UvlelKvu+koSQRQiyBJGISEB0hFYZwW1sp4/2csaZ1jlz5sz80cc5c7rnaI8zju2o09qiIrKowEGURXYI+5IASQjZl0plT2rff3/073O/byVVlUQSRPs+5+QkkMpbb9333ud+lufzuaJgMBgEBwcHxw8UYj4EHBwcnMQ4ODg4OIlxcHBwcBLj4ODgJMbBwcHBSYyDg4ODkxgHBwcHJzEODg5OYhwcHBycxDg4ODg4iXFw/GWAqgHHfv+ufyv8+q7XvVMh4rWTHBwc3BLj4ODg4CTGwcHBwUmMg4ODkxgHBwcHJzEODg4OTmIcHBwcnMQ4ODg4iXFwcHBwEuPg4ODgJMbBwcHBSYyDg+NHBCkfAo6/ZAiLov1+P3w+H/x+P/x+P4LBIDQaDWQyGR+oSYyjSCSK+G9OYhwctwiHw4Guri44HA54vV74fD72FQgE4Pf74Xa74XA44Ha74Xa7EQgEsGrVKqSnp9+2BTlT5EJETT+PJZxgMAixWMx+FwgEIBb/2VGjn4W/o78P9/qxPSXo2pFez0mMg2MSC7mrqwt/+MMfYDabYbfb4XQ64XQ64XA42HeHw4HBwUFIJBJIJBLExMRg4cKFSEtL+0GSmJAwxsLn80EqlSIQCIRYomKxGD6fD2KxGH6/n31un88HiUQCv98fcm26Pr2H0LIlkhLeg/B3RIbCa30XS46TGMePHn6/HxaLBVu2bIFcLg9ZXLRQpFIpDAYDDAYDW0RyufwHRV7ChU9kQeQhtHyInLxeL7OwyFoS/o7caCI1+k7XlEgkjIiEJOXz+SASiSCVStl9SKX/j2pE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";

    // avoid inlining of constant
    private static String crashImageWrapper() {
        return crashImage;
    }

    @JSBody(params = {}, script = "if(window.eaglercraftOpts) { return (typeof window.eaglercraftOpts === \"string\") ? window.eaglercraftOpts :"
            + "JSON.stringify(window.eaglercraftOpts); } else { return null; }")
    private static native String getEaglerOpts();

    public static HTMLElement rootElement = null;
    public static Minecraft instance = null;

    public static void main(String[] args) {
        String newArgs = getEaglerOpts();
        if (newArgs != null) {
            crashScreenOptsDump = "window.eaglercraftOpts = " + newArgs;
            try {
                newMain(new JSONObject(newArgs));
            } catch (JSONException ex) {
                Window.alert("There's a JSON syntax error in window.eaglercraftOpts:\n" + ex);
                ex.printStackTrace();
            }
        } else {
            oldMain();
        }
    }

    private static String crashScreenOptsDump = null;

    private static void newMain(JSONObject conf) {

        String containerEl = conf.getString("container");

        rootElement = Window.current().getDocument().getElementById(containerEl);
        if (rootElement == null) {
            throw new JSONException("Container element \"" + containerEl + "\" does not exist in page");
        }

        EaglerAdapterImpl2.setServerToJoinOnLaunch(conf.optString("joinServer", null));

        String assetsURI = conf.getString("assetsURI");
        if (assetsURI.length() > 256) {
            conf.put("assetsURI", assetsURI.substring(0, 256) + " ... ");
            crashScreenOptsDump = "window.eaglercraftOpts = " + conf;
        }
        String serverWorkerURI = conf.optString("serverWorkerURI", null);
        EaglerAdapterImpl2.setWorldDatabaseName(conf.optString("worldsFolder", "MAIN"));

        registerErrorHandler();

        try {

            EaglerAdapterImpl2.initializeContext(rootElement, assetsURI, serverWorkerURI);

            ServerList.loadDefaultServers(conf);
            AssetRepository.loadOverrides(conf);
            LocalStorageManager.loadStorage();

            run0();

        } catch (Throwable t) {
            showCrashScreen(t + "\n\n" + getStackTrace(t));
        }
    }

    private static void oldMain() {
        String[] e = getOpts();
        crashScreenOptsDump = "window.minecraftOpts = [ ";
        for (int i = 0; i < e.length; ++i) {
            String sh = e[i].length() > 512 ? (e[i].substring(0, 512) + "...") : e[i];
            if (i > 0) {
                crashScreenOptsDump += ", ";
            }
            crashScreenOptsDump += "\"" + sh + "\"";
        }
        crashScreenOptsDump += " ]";

        registerErrorHandler();

        try {

            EaglerAdapterImpl2.initializeContext(rootElement = Window.current().getDocument().getElementById(e[0]), e[1], "worker_bootstrap.js");

            LocalStorageManager.loadStorage();
            if (e.length > 2 && e[2].length() > 0) {
                ServerList.loadDefaultServers(e[2]);
            }
            if (e.length > 3) {
                EaglerAdapterImpl2.setServerToJoinOnLaunch(e[3]);
            }

            run0();

        } catch (Throwable t) {
            showCrashScreen(t + "\n\n" + getStackTrace(t));
        }
    }

    private static String getStackTrace(Throwable t) {
        JSObject obj = JSExceptions.getJSException(t);
        if (obj != null) {
            JSError err = (JSError) obj;
            return err.getStack() == null ? "[no stack trace]" : err.getStack();
        } else {
            return "[no stack trace]";
        }
    }

    private static void run0() {
        System.out.println(" -------- starting minecraft -------- ");
        instance = new Minecraft();
        run1();
    }

    private static void run1() {
        instance.run();
    }

    @JSBody(params = {}, script = "return window.minecraftOpts;")
    public static native String[] getOpts();

    public static void registerErrorHandler() {
        setWindowErrorHandler(new WindowErrorHandler() {

            @Override
            public void call(String message, String file, int line, int col, JSError error) {
                StringBuilder str = new StringBuilder();

                str.append("Native Browser Exception\n");
                str.append("----------------------------------\n");
                str.append("  Line: ").append((file == null ? "unknown" : file) + ":" + line + ":" + col).append('\n');
                str.append("  Type: ").append(error == null ? "generic" : error.getName()).append('\n');

                if (error != null) {
                    str.append("  Desc: ").append(error.getMessage() == null ? "null" : error.getMessage()).append('\n');
                }

                if (message != null) {
                    if (error == null || error.getMessage() == null || !message.endsWith(error.getMessage())) {
                        str.append("  Desc: ").append(message).append('\n');
                    }
                }

                str.append("----------------------------------\n\n");
                str.append(error.getStack() == null ? "No stack trace is available" : error.getStack()).append('\n');

                showCrashScreen(str.toString());
            }

        });
    }

    @JSFunctor
    private interface WindowErrorHandler extends JSObject {
        void call(String message, String file, int line, int col, JSError error);
    }

    @JSBody(params = {"handler"}, script = "window.addEventListener(\"error\", function(e) { handler("
            + "(typeof e.message === \"string\") ? e.message : null,"
            + "(typeof e.filename === \"string\") ? e.filename : null,"
            + "(typeof e.lineno === \"number\") ? e.lineno : 0,"
            + "(typeof e.colno === \"number\") ? e.colno : 0,"
            + "(typeof e.error === \"undefined\") ? null : e.error); });")
    public static native void setWindowErrorHandler(WindowErrorHandler handler);

    private static boolean isCrashed = false;

    private static void showCrashScreen(String t) {
        if (!isCrashed) {
            isCrashed = true;

            StringBuilder str = new StringBuilder();
            str.append("Game Crashed! I have fallen and I can't get up! If this has happened more than once then please copy the text on this screen and publish it in the issues feed of this fork's GitHub repository.\n\nThe URL to this fork's GitHub repository is: " + ConfigConstants.githubRepo + "\n\n");
            str.append(t);
            str.append('\n').append('\n');
            str.append("eaglercraft.version = \"").append(ConfigConstants.version).append("\"\n");
            str.append("eaglercraft.minecraft = \"1.5.2\"\n");
            str.append("eaglercraft.brand = \"lax1dude\"\n");
            str.append("eaglercraft.username = \"").append(EaglerProfile.username).append("\"\n");
            str.append('\n');
            str.append(addWebGLToCrash());
            str.append('\n');
            str.append(crashScreenOptsDump).append('\n');
            str.append('\n');
            addDebugNav(str, "userAgent");
            addDebugNav(str, "vendor");
            addDebugNav(str, "language");
            addDebugNav(str, "hardwareConcurrency");
            addDebugNav(str, "deviceMemory");
            addDebugNav(str, "platform");
            addDebugNav(str, "product");
            str.append('\n');
            str.append("rootElement.clientWidth = ").append(rootElement.getClientWidth()).append('\n');
            str.append("rootElement.clientHeight = ").append(rootElement.getClientHeight()).append('\n');
            addDebug(str, "innerWidth");
            addDebug(str, "innerHeight");
            addDebug(str, "outerWidth");
            addDebug(str, "outerHeight");
            addDebug(str, "devicePixelRatio");
            addDebugScreen(str, "availWidth");
            addDebugScreen(str, "availHeight");
            addDebugScreen(str, "colorDepth");
            addDebugScreen(str, "pixelDepth");
            str.append('\n');
            addDebugLocation(str, "href");
            str.append("\n----- Begin Minecraft Config -----\n");
            str.append(LocalStorageManager.dumpConfiguration());
            str.append("\n----- End Minecraft Config -----\n\n");
            addDebug(str, "minecraftServer");

            String s = rootElement.getAttribute("style");
            rootElement.setAttribute("style", (s == null ? "" : s) + "position:relative;");
            HTMLDocument doc = Window.current().getDocument();
            HTMLElement img = doc.createElement("img");
            HTMLElement div = doc.createElement("div");
            img.setAttribute("style", "z-index:100;position:absolute;top:10px;left:calc(50% - 151px);");
            img.setAttribute("src", crashImageWrapper());
            div.setAttribute("style", "z-index:100;position:absolute;top:135px;left:10%;right:10%;bottom:30px;background-color:white;border:1px solid #cccccc;overflow-x:hidden;overflow-y:scroll;overflow-wrap:break-word;white-space:pre-wrap;font: 14px monospace;padding:10px;");
            rootElement.appendChild(img);
            rootElement.appendChild(div);
            div.appendChild(doc.createTextNode(str.toString()));

            EaglerAdapterImpl2.removeEventHandlers();

        }
    }

    private static String addWebGLToCrash() {
        StringBuilder ret = new StringBuilder();

        WebGLRenderingContext ctx = EaglerAdapterImpl2.webgl;

        if (ctx == null) {
            HTMLCanvasElement cvs = (HTMLCanvasElement) Window.current().getDocument().createElement("canvas");

            cvs.setWidth(64);
            cvs.setHeight(64);

            ctx = (WebGLRenderingContext) cvs.getContext("webgl");
        }

        if (ctx != null) {
            if (EaglerAdapterImpl2.webgl != null) {
                ret.append("webgl.version = ").append(ctx.getParameterString(VERSION)).append('\n');
            }
            if (ctx.getExtension("WEBGL_debug_renderer_info") != null) {
                ret.append("webgl.renderer = ").append(ctx.getParameterString(/* UNMASKED_RENDERER_WEBGL */ 0x9246)).append('\n');
                ret.append("webgl.vendor = ").append(ctx.getParameterString(/* UNMASKED_VENDOR_WEBGL */ 0x9245)).append('\n');
            } else {
                ret.append("webgl.renderer = ").append("" + ctx.getParameterString(RENDERER) + " [masked]").append('\n');
                ret.append("webgl.vendor = ").append("" + ctx.getParameterString(VENDOR) + " [masked]").append('\n');
            }
            ret.append("\nwebgl.anisotropicGlitch = ").append(DetectAnisotropicGlitch.hasGlitch()).append('\n');
        } else {
            ret.append("Failed to query GPU info!\n");
        }

        return ret.toString();
    }

    public static void showIncompatibleScreen(String t) {
        if (!isCrashed) {
            isCrashed = true;

            String s = rootElement.getAttribute("style");
            rootElement.setAttribute("style", (s == null ? "" : s) + "position:relative;");
            HTMLDocument doc = Window.current().getDocument();
            HTMLElement img = doc.createElement("img");
            HTMLElement div = doc.createElement("div");
            img.setAttribute("style", "z-index:100;position:absolute;top:10px;left:calc(50% - 151px);");
            img.setAttribute("src", crashImageWrapper());
            div.setAttribute("style", "z-index:100;position:absolute;top:135px;left:10%;right:10%;bottom:30px;background-color:white;border:1px solid #cccccc;overflow-x:hidden;overflow-y:scroll;font:18px sans-serif;padding:40px;");
            rootElement.appendChild(img);
            rootElement.appendChild(div);
            div.setInnerHTML("<h2><svg style=\"vertical-align:middle;margin:0px 16px 8px 8px;\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\" fill=\"none\"><path stroke=\"#000000\" stroke-width=\"3\" stroke-linecap=\"square\" d=\"M1.5 8.5v34h45v-28m-3-3h-10v-3m-3-3h-10m15 6h-18v-3m-3-3h-10\"/><path stroke=\"#000000\" stroke-width=\"2\" stroke-linecap=\"square\" d=\"M12 21h0m0 4h0m4 0h0m0-4h0m-2 2h0m20-2h0m0 4h0m4 0h0m0-4h0m-2 2h0\"/><path stroke=\"#000000\" stroke-width=\"2\" stroke-linecap=\"square\" d=\"M20 30h0 m2 2h0 m2 2h0 m2 2h0 m2 -2h0 m2 -2h0 m2 -2h0\"/></svg>+ This device is incompatible with Eaglercraft&ensp;:(</h2>"
                    + "<div style=\"margin-left:40px;\">"
                    + "<p style=\"font-size:1.2em;\"><b style=\"font-size:1.1em;\">Issue:</b> <span style=\"color:#BB0000;\" id=\"crashReason\"></span><br /></p>"
                    + "<p style=\"margin-left:10px;font:0.9em monospace;\" id=\"crashUserAgent\"></p>"
                    + "<p style=\"margin-left:10px;font:0.9em monospace;\" id=\"crashWebGL\"></p>"
                    + "<p><br /><span style=\"font-size:1.1em;border-bottom:1px dashed #AAAAAA;padding-bottom:5px;\">Things you can try:</span></p>"
                    + "<ol>"
                    + "<li><span style=\"font-weight:bold;\">Just try using Eaglercraft on a different device</span>, it isn't a bug it's common sense</li>"
                    + "<li style=\"margin-top:7px;\">If you are on a mobile device, please try a proper desktop or a laptop computer</li>"
                    + "<li style=\"margin-top:7px;\">If you are using a device with no mouse cursor, please use a device with a mouse cursor</li>"
                    + "<li style=\"margin-top:7px;\">If you are not using Chrome/Edge, try installing the latest Google Chrome</li>"
                    + "<li style=\"margin-top:7px;\">If your browser is out of date, please update it to the latest version</li>"
                    + "<li style=\"margin-top:7px;\">If you are using an old OS such as Windows 7, please try Windows 10 or 11</li>"
                    + "<li style=\"margin-top:7px;\">If you have a GPU launched before 2009, WebGL 2.0 support may be impossible</li>"
                    + "</ol>"
                    + "</div>");

            div.querySelector("#crashReason").appendChild(doc.createTextNode(t));
            div.querySelector("#crashUserAgent").appendChild(doc.createTextNode(getStringNav("userAgent")));

            EaglerAdapterImpl2.removeEventHandlers();

            String webGLRenderer = "No GL_RENDERER string could be queried";

            try {
                HTMLCanvasElement cvs = (HTMLCanvasElement) Window.current().getDocument().createElement("canvas");

                cvs.setWidth(64);
                cvs.setHeight(64);

                WebGLRenderingContext ctx = (WebGLRenderingContext) cvs.getContext("webgl");

                if (ctx != null) {
                    String r;
                    if (ctx.getExtension("WEBGL_debug_renderer_info") != null) {
                        r = ctx.getParameterString(/* UNMASKED_RENDERER_WEBGL */ 0x9246);
                    } else {
                        r = ctx.getParameterString(RENDERER);
                        if (r != null) {
                            r += " [masked]";
                        }
                    }
                    if (r != null) {
                        webGLRenderer = r;
                    }
                }
            } catch (Throwable tt) {
            }

            div.querySelector("#crashWebGL").appendChild(doc.createTextNode(webGLRenderer));

        }
    }

    @JSBody(params = {"v"}, script = "try { return \"\"+window[v]; } catch(e) { return \"<error>\"; }")
    private static native String getString(String var);

    @JSBody(params = {"v"}, script = "try { return \"\"+window.navigator[v]; } catch(e) { return \"<error>\"; }")
    private static native String getStringNav(String var);

    @JSBody(params = {"v"}, script = "try { return \"\"+window.screen[v]; } catch(e) { return \"<error>\"; }")
    private static native String getStringScreen(String var);

    @JSBody(params = {"v"}, script = "try { return \"\"+window.location[v]; } catch(e) { return \"<error>\"; }")
    private static native String getStringLocation(String var);

    private static void addDebug(StringBuilder str, String var) {
        str.append("window.").append(var).append(" = ").append(getString(var)).append('\n');
    }

    private static void addDebugNav(StringBuilder str, String var) {
        str.append("window.navigator.").append(var).append(" = ").append(getStringNav(var)).append('\n');
    }

    private static void addDebugScreen(StringBuilder str, String var) {
        str.append("window.screen.").append(var).append(" = ").append(getStringScreen(var)).append('\n');
    }

    private static void addDebugLocation(StringBuilder str, String var) {
        str.append("window.location.").append(var).append(" = ").append(getStringLocation(var)).append('\n');
    }

    private static void addArray(StringBuilder str, String var) {
        str.append("window.").append(var).append(" = ").append(getArray(var)).append('\n');
    }

    @JSBody(params = {"v"}, script = "try { return (typeof window[v] !== \"undefined\") ? JSON.stringify(window[v]) : \"[\\\"<error>\\\"]\"; } catch(e) { return \"[\\\"<error>\\\"]\"; }")
    private static native String getArray(String var);

}
