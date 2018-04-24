/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.chilkatsoft;

public class CkTrustedRoots {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected CkTrustedRoots(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CkTrustedRoots obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        chilkatJNI.delete_CkTrustedRoots(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public CkTrustedRoots() {
    this(chilkatJNI.new_CkTrustedRoots(), true);
  }

  public void LastErrorXml(CkString str) {
    chilkatJNI.CkTrustedRoots_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorHtml(CkString str) {
    chilkatJNI.CkTrustedRoots_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorText(CkString str) {
    chilkatJNI.CkTrustedRoots_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void put_EventCallbackObject(CkBaseProgress progress) {
    chilkatJNI.CkTrustedRoots_put_EventCallbackObject(swigCPtr, this, CkBaseProgress.getCPtr(progress), progress);
  }

  public void get_DebugLogFilePath(CkString str) {
    chilkatJNI.CkTrustedRoots_get_DebugLogFilePath(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String debugLogFilePath() {
    return chilkatJNI.CkTrustedRoots_debugLogFilePath(swigCPtr, this);
  }

  public void put_DebugLogFilePath(String newVal) {
    chilkatJNI.CkTrustedRoots_put_DebugLogFilePath(swigCPtr, this, newVal);
  }

  public void get_LastErrorHtml(CkString str) {
    chilkatJNI.CkTrustedRoots_get_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorHtml() {
    return chilkatJNI.CkTrustedRoots_lastErrorHtml(swigCPtr, this);
  }

  public void get_LastErrorText(CkString str) {
    chilkatJNI.CkTrustedRoots_get_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorText() {
    return chilkatJNI.CkTrustedRoots_lastErrorText(swigCPtr, this);
  }

  public void get_LastErrorXml(CkString str) {
    chilkatJNI.CkTrustedRoots_get_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorXml() {
    return chilkatJNI.CkTrustedRoots_lastErrorXml(swigCPtr, this);
  }

  public boolean get_LastMethodSuccess() {
    return chilkatJNI.CkTrustedRoots_get_LastMethodSuccess(swigCPtr, this);
  }

  public void put_LastMethodSuccess(boolean newVal) {
    chilkatJNI.CkTrustedRoots_put_LastMethodSuccess(swigCPtr, this, newVal);
  }

  public int get_NumCerts() {
    return chilkatJNI.CkTrustedRoots_get_NumCerts(swigCPtr, this);
  }

  public boolean get_TrustSystemCaRoots() {
    return chilkatJNI.CkTrustedRoots_get_TrustSystemCaRoots(swigCPtr, this);
  }

  public void put_TrustSystemCaRoots(boolean newVal) {
    chilkatJNI.CkTrustedRoots_put_TrustSystemCaRoots(swigCPtr, this, newVal);
  }

  public boolean get_VerboseLogging() {
    return chilkatJNI.CkTrustedRoots_get_VerboseLogging(swigCPtr, this);
  }

  public void put_VerboseLogging(boolean newVal) {
    chilkatJNI.CkTrustedRoots_put_VerboseLogging(swigCPtr, this, newVal);
  }

  public void get_Version(CkString str) {
    chilkatJNI.CkTrustedRoots_get_Version(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String version() {
    return chilkatJNI.CkTrustedRoots_version(swigCPtr, this);
  }

  public boolean Activate() {
    return chilkatJNI.CkTrustedRoots_Activate(swigCPtr, this);
  }

  public boolean AddCert(CkCert cert) {
    return chilkatJNI.CkTrustedRoots_AddCert(swigCPtr, this, CkCert.getCPtr(cert), cert);
  }

  public boolean AddJavaKeyStore(CkJavaKeyStore keystore) {
    return chilkatJNI.CkTrustedRoots_AddJavaKeyStore(swigCPtr, this, CkJavaKeyStore.getCPtr(keystore), keystore);
  }

  public CkTask AddJavaKeyStoreAsync(CkJavaKeyStore keystore) {
    long cPtr = chilkatJNI.CkTrustedRoots_AddJavaKeyStoreAsync(swigCPtr, this, CkJavaKeyStore.getCPtr(keystore), keystore);
    return (cPtr == 0) ? null : new CkTask(cPtr, true);
  }

  public boolean Deactivate() {
    return chilkatJNI.CkTrustedRoots_Deactivate(swigCPtr, this);
  }

  public CkCert GetCert(int index) {
    long cPtr = chilkatJNI.CkTrustedRoots_GetCert(swigCPtr, this, index);
    return (cPtr == 0) ? null : new CkCert(cPtr, true);
  }

  public boolean LoadCaCertsPem(String path) {
    return chilkatJNI.CkTrustedRoots_LoadCaCertsPem(swigCPtr, this, path);
  }

  public CkTask LoadCaCertsPemAsync(String path) {
    long cPtr = chilkatJNI.CkTrustedRoots_LoadCaCertsPemAsync(swigCPtr, this, path);
    return (cPtr == 0) ? null : new CkTask(cPtr, true);
  }

  public boolean SaveLastError(String path) {
    return chilkatJNI.CkTrustedRoots_SaveLastError(swigCPtr, this, path);
  }

}
