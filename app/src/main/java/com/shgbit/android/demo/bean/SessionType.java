package com.shgbit.android.demo.bean;

public class SessionType {
    private DeviceState ContentOnlyState;
    private DeviceState MobileState;
    private DeviceState PCState;
    private DeviceState TerminalState;

    public SessionType() {
    }

    public DeviceState getContentOnlyState() {
        return this.ContentOnlyState;
    }

    public void setContentOnlyState(DeviceState contentOnlyState) {
        this.ContentOnlyState = contentOnlyState;
    }

    public DeviceState getMobileState() {
        return this.MobileState;
    }

    public void setMobileState(DeviceState mobileState) {
        this.MobileState = mobileState;
    }

    public DeviceState getPCState() {
        return this.PCState;
    }

    public void setPCState(DeviceState PCState) {
        this.PCState = PCState;
    }

    public DeviceState getTerminalState() {
        return this.TerminalState;
    }

    public void setTerminalState(DeviceState terminalState) {
        this.TerminalState = terminalState;
    }
}
