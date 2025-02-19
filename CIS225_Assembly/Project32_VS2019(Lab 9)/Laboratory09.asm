; Sawyer Davis - Laboratory 09
;Date: 04/28/2024
;Due:  04/30/2024

include Irvine32.inc

COMMENT *
This program will convert user inputted numbers to bases 2-16 until the user decides to quit.
*

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	num			word	?
	base		word	?
	choice		byte	'Z'
	msg1		byte	"Enter the number(POSITIVE): ", 0
	msg2		byte	"Enter the base (2-16): ", 0
	msg3		byte	"The converted number is ", 0
	msg4		byte	"Would you like to continue(Y/N): ", 0

.code
main proc

	.WHILE(choice != 'N')
		push	OFFSET num
		Call	GetNum
		add		esp, 4
		push	OFFSET base
		Call	GetBase
		add		esp, 4
		mov		edx, OFFSET msg3
		call	WriteString
		push	base
		push	num
		Call	Convert
		add		esp, 4
		call	Crlf
		push	OFFSET choice
		Call	GetChoice
		add		esp, 4

	.ENDW

invoke ExitProcess,0
main endp

;Get Number
GetNum proc
	push	ebp
	mov		ebp, esp

	mov		edx, OFFSET msg1
	call	WriteString
	call	ReadInt
	.WHILE(SIGN?)
		mov		edx, OFFSET msg1
		call	WriteString
		call	ReadInt
	.ENDW
	mov		edi, [ebp + 8]
	mov		[edi], ax
	
	pop		ebp
	ret
GetNum endp

; Get Base
GetBase proc
	push	ebp
	mov		ebp, esp

		mov		edx, OFFSET msg2
		call	WriteString
		call	ReadInt 
		jmp		c1 
eI:		
		mov		edx, OFFSET msg2
		call	WriteString
		call	ReadInt
c1:		
		cmp		eax, 2
		jl		eI
		cmp		eax, 16
		jg		eI
	
	mov		edi, [ebp + 8]
	mov		[edi], ax

	pop		ebp
	ret
GetBase endp

GetChoice proc
	push	ebp
	mov		ebp, esp

ask:	mov		edx, OFFSET msg4
		call	WriteString
		call	ReadChar
		call	WriteChar
		call	Crlf
		cmp		al, 'Y'
		je		quit
		cmp		al, 'N'
		je		quit
		jmp		ask
quit:

	mov		edi, [ebp + 8]
	mov		[edi], al
	
	pop		ebp
	ret
GetChoice endp

Convert proc
    push	ebp
    mov		ebp, esp
    sub		esp, 8
    movzx	eax, WORD PTR [ebp + 8]  
    movzx	ebx, WORD PTR [ebp + 10] 
	.if(WORD PTR [ebp + 8] != 0)
		mov		edx, 0
		div		ebx
		mov		[ebp - 4], eax 
		mov		[ebp - 8], edx 
		mov		bx, [ebp + 10]
		push	bx
		push	ax
		call	Convert
		add		esp, 4

		movzx eax, byte ptr [ebp - 8]
		.if(al >= 10)
			add	al, 55
		.else
			add al, 48
		.endif
		call WriteChar
	.endif
	add		esp, 8
    pop		ebp
    ret
Convert endp

end main