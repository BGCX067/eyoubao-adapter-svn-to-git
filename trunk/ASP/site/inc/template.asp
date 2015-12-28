<%
'--- --- ---
' template_cls.asp
'
' version 1.5
'---- ---- ----
' (c)2000 - 2002 James Q. Stansfield (jqs@iridani.net)
' This code is free to be used by anyone provided that the copyright is retained in the script.
' This code may be redistributed provided the copyright is retained in the script.
'----- ----- -----
option explicit
const cTEMPLATE_VERSION = 1.5

'Define our token type constants
const STRINGVARIABLE = 1
const INCLUDEANDPARSE = 2
const INCLUDEANDNOPARSE = 3
const PARSESTRING = 5
const PARSEARRAY = 6

class template_cls
    '---
    'Declarations
    '---
    private gstrTemplateFile
    private gobjTokens
    private gblnReturn
    private gstrHTML
    private gstrOpenTag
    private gstrCloseTag
    '---
    'Create & Destroy Methods
    '---
    private sub Class_Initialize
        set gobjTokens = createobject("Scripting.Dictionary")
        gobjTokens.CompareMode = 1
        gblnReturn = False
        gstrHTML = ""
        gstrOpenTag = "[%"
        gstrCloseTag = "%]"
    end sub
    '---
    private sub Class_Terminate
        set gobjTokens = nothing
    end sub
    '---
    'Properties
    '---
    public property let TemplateFile(inString)
        gstrTemplateFile = inString
    end property
    '---
    public property let OpenTag(inString)
        gstrOpenTag = inString
    end property
    '---
    public property let CloseTag(inString)
        gstrCloseTag = inString
    end property
    '---
    public property let CI(inBool)
        if vartype(inbool) = 11 then
            if inBool then
                inBool = 1
            else
                inBool = 0
            end if
            gobjTokens.CompareMode = inBool
        end if
    end property
    '---
    'Subroutines
    '---
    public sub AddToken(inToken, inType, inData)
        if len(inToken) > 0 then
            if gobjTokens.Exists(inToken) then
                gobjTokens.Item(inToken) = array(inType, inData)
            else
                gobjTokens.Add inToken, array(inType, inData)
            end if
        end if
    end sub
    '---
    public sub DelToken(inToken)
        if len(inToken) > 0 then
            if gobjTokens.Exists(inToken) then
                gobjTokens.Remove(inToken)
            end if
        end if
    end sub
    '---
    public sub RemoveAllTokens
        gobjTokens.RemoveAll
    end sub
    '---
    public sub parseTemplateFile
        parseFile(gstrTemplateFile)
    end sub
    
    public sub parseTemplateString(inString)
        parseString(inString)
    end sub
    '---
    'Functions
    '---
    public function getParsedTemplateFile
        gstrHTML = ""
        gblnReturn = true
        getParsedTemplateFile = parseFile(gstrTemplateFile)
    end function
    '---
    public function getParsedTemplateString(inString)
        gstrHTML = ""
        gblnReturn = true
        getParsedTemplateString = parseString(inString)
    end function
    '---
    private function parseString(inString)
        dim astrData
        if len(inString) > 0 then
            astrData = split(inString & vbCrLf, VbCrLf)
            call parseArray(astrData)
        end if
        if gblnReturn then parseString = gstrHTML
    end function
    '---
    private function parseFile(inFile)
        if len(inFile) > 0 then
            if fileExists(inFile) then
                call parseArray(loadFile(inFile, 10))
            end if
        end if
        if gblnReturn then parseFile = gstrHTML
    end function
    '---
    private sub parseArray(inArray)
        dim strLine
        for each strLine in inArray
            call parseLine(strLine & VbCrLf)
        next
    end sub
    '---
    private sub parseLine(inLine)
        dim intA, intB, strLine, strLine2, strToken
        intA = instr(inLine, gstrOpenTag)
        if intA > 0 then
            strLine = left(inLine, intA - 1)
            intB = instr(inLine, gstrCloseTag)
            strLine2 = mid(inLine, intB + len(gstrCloseTag))
            strToken = trim(mid(inLine, intA + len(gstrOpenTag), (intB - intA - len(gstrOpenTag))))
            call printLine(strLine)
            call getToken(strToken)
            call parseLine(strLine2)
        else
            call printLine(inLine)
        end if
    end sub
    '---
    private sub getToken(inToken)
        if gobjTokens.Exists(inToken) then
            dim intTokenType, strTokenData
            intTokenType = gobjTokens.Item(inToken)(0)
            strTokenData = gobjTokens.Item(inToken)(1)
            select case intTokenType
                case 1  'Display Variable
                    call printLine(strTokenData)
                case 2  'Load File, Parse & Display
                    call parseArray(loadFile(strTokenData, 10))
                case 3  'Load File & Display
                    call printLine(loadFile(strTokenData, 1))
                case 5  'Parse String
                    call parseLine(strTokenData)
                case 6  'Parse Array
                    call parseArray(strTokenData)
            end select
        end if
    end sub
    '---
    public function fileExists(inFile)
        dim objFSO
        set objFSO = createobject("Scripting.FileSystemObject")
        if objFSO.FileExists(Server.Mappath(inFile)) then FileExists = True
        set objFSO = nothing
    end function
    '---
    public function loadFile(inFileName, inType)
        dim objFSO, objFile, objStream, strText, stream
        if fileExists(inFileName) then
            
            set stream = createobject("ADODB.Stream")
            stream.charset = "UTF-8"
            stream.mode = 3
            stream.open()
            stream.loadFromFile(Server.Mappath(inFileName))
            strText = stream.ReadText
            stream.close()
            
            
            select case cint(inType)
                case 0  'Load & Display
                    printLine(strText)
                case 1  'Load & Return
                    loadFile = strText
                case 10 'Load & Return As Array
                    loadFile = split(strText & VbCrLf, VbCrLf)
            end select
        end if
    end function
    '---
    private function printLine(inLine)
        if gblnReturn then
            gstrHTML = gstrHTML & inLine
        else
            response.write(inLine)
        end if
    end function
end class
%>