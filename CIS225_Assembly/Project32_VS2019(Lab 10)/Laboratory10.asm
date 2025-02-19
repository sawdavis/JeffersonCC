; Sawyer Davis - Laboratory 10
;Date: 04/30/2024
;Due:  05/06/2024

include Irvine32.inc

COMMENT *
This program will convert user inputted numbers to bases 2-16 until the user decides to quit.
*

.386
;.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	em1			real8	-1.10, 2.25, 0.0, -0.06
	em2			real8	-2.15, 4.25, 9.10, -4.06
	em3			real8	10.80, -3.33, 0.20
	em4			real8   14.12, 0.45, -9.43, -5.2, 7.23
	totals		real8	4 DUP(0.0)
	average		real8	4 DUP(?)
	msg1		byte	"Total: ", 0
	msg2		byte	"Average: ", 0
	msg3		byte	"-Employee 1-", 0
	msg4		byte	"-Employee 2-", 0
	msg5		byte	"-Employee 3-", 0
	msg6		byte	"-Employee 4-", 0

.code
main proc
	;Employee 1
	mov		edx, OFFSET msg3
	call	WriteString
	call Crlf
	push	OFFSET em1
	push	OFFSET totals
	push	OFFSET average
	mov		eax, LENGTHOF em1
	push	ax
	call	Compute
	add		esp, 14
	push	OFFSET totals
	push	OFFSET average
	call	Display
	add		esp, 8

	;Employee 2
	mov		edx, OFFSET msg4
	call	WriteString
	call Crlf
	push	OFFSET em2
	push	OFFSET totals + 8
	push	OFFSET average + 8
	mov		eax, LENGTHOF em2
	push	ax
	call	Compute
	add		esp, 14
	push	OFFSET totals + 8
	push	OFFSET average + 8
	call	Display
	add		esp, 8

	;Employee 3
	mov		edx, OFFSET msg5
	call	WriteString
	call Crlf
	push	OFFSET em3
	push	OFFSET totals + 16
	push	OFFSET average + 16
	mov		eax, LENGTHOF em3
	push	ax
	call	Compute
	add		esp, 14
	push	OFFSET totals + 16
	push	OFFSET average + 16
	call	Display
	add		esp, 8

	;Employee 4
	mov		edx, OFFSET msg6
	call	WriteString
	call Crlf
	push	OFFSET em4
	push	OFFSET totals + 24
	push	OFFSET average + 24
	mov		eax, LENGTHOF em4
	push	ax
	call	Compute
	add		esp, 14
	push	OFFSET totals + 24
	push	OFFSET average + 24
	call	Display
	add		esp, 8


invoke ExitProcess,0
main endp

Compute proc
	push	ebp
	mov		ebp, esp
	pushad

	mov		esi, [ebp + 18]
	movzx		ecx, WORD PTR[ebp + 8]
	fld		real8 ptr [esi]
	dec	ecx
	add		esi, 8
L1:	
	fld		real8 ptr [esi]
	faddp
	add		esi, 8
	loop L1
	
	mov		esi, [ebp + 14]
	fst		REAL8 ptr [esi]

	fild	word ptr [ebp + 8]
	fdivp	st(1), st(0)
	
	mov		esi, [ebp + 10]
	fstp	REAL8 ptr [esi]

	popad
	pop		ebp
	ret
Compute endp

Display proc
	push	ebp
	mov		ebp, esp
	pushad

	mov		edx, OFFSET msg1
	call	WriteString
	mov		esi, [ebp + 12]
	fld		REAL8 PTR [esi]
	call	WriteFloat
	call	Crlf

	mov		edx, OFFSET msg2
	call	WriteString
	mov		esi, [ebp + 8]
	fld		REAL8 PTR [esi]
	call	WriteFloat
	call	Crlf
	
	popad
	pop		ebp
	ret
Display endp

end main