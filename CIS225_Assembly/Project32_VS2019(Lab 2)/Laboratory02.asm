; Sawyer Davis - Laboratory 02
;Date: 02/13/2024
;Due:  02/20/2024

COMMENT *
This program manages information for snow blowing business. The program will take the dimensions of the driveway, calculate a square footage,
and then calcuate the number of passes and the time it will take. Using this information an hourly cost and maintnence cost will be applied. 
Lastly a total will be computed.
*

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	capacity	dword	20, 15, 10, 5, 1
	cost		dword	2900, 2575, 2150, 1625, 1135
	shovelCost	dword	1425
	cases		dword	?, ?, ?, ?, ?
	sCost		dword	0, 0, 0, 0, 0
	pCost		dword   0, 0, 0, 0, 0
	totals		dword	?, ?, ?
	order		dword 52
.code
main proc
	;Calculate Cases XL
	mov edx, 0
	mov eax, [order]
	mov ebx, [capacity]
	div	ebx
	mov [cases], eax
	mov [order], edx

	;Calculate Product Cost XL
	mul [capacity]
	mul [shovelCost]
	mov [pCost], eax

	;Calculate Shipping Cost XL
	mov eax, [cases]
	mul [cost]
	mov [sCost], eax

	;Calculate Cases L
	mov edx, 0
	mov eax, [order]
	mov ebx, [capacity+4]
	div	ebx
	mov [cases+4], eax
	mov [order], edx

	;Calculate Product Cost L
	mul [capacity+4]
	mul [shovelCost]
	mov [pCost+4], eax

	;Calculate Shipping Cost L
	mov eax, [cases+4]
	mul [cost+4]
	mov [sCost+4], eax
	

	;Calculate Cases M
	mov edx, 0
	mov eax, [order]
	mov ebx, [capacity+8]
	div	ebx
	mov [cases+8], eax
	mov [order], edx

	;Calculate Product Cost M
	mul [capacity+8]
	mul [shovelCost]
	mov [pCost+8], eax

	;Calculate Shipping Cost M
	mov eax, [cases+8]
	mul [cost+8]
	mov [sCost+8], eax


	;Calculate Cases S
	mov edx, 0
	mov eax, [order]
	mov ebx, [capacity+12]
	div	ebx
	mov [cases+12], eax
	mov [order], edx

	;Calculate Product Cost S
	mul [capacity+12]
	mul [shovelCost]
	mov [pCost+12], eax

	;Calculate Shipping Cost S
	mov eax, [cases+12]
	mul [cost+12]
	mov [sCost+12], eax


	;Calculate Cases I
	mov edx, 0
	mov eax, [order]
	mov ebx, [capacity+16]
	div	ebx
	mov [cases+16], eax
	mov [order], edx

	;Calculate Product Cost I
	mul [capacity+16]
	mul [shovelCost]
	mov [pCost+16], eax

	;Calculate Shipping Cost I
	mov eax, [cases+16]
	mul [cost+16]
	mov [sCost+16], eax

	;Calculate Totals
	mov eax, [sCost]
	add eax, [sCost+4]
	add eax, [sCost+8]
	add eax, [sCost+12]
	add eax, [sCost+16]
	mov [totals], eax
	mov eax, [pCost]
	add eax, [pCost+4]
	add eax, [pCost+8]
	add eax, [pCost+12]
	add eax, [pCost+16]
	mov [totals+4], eax
	add eax, [totals]
	mov [totals+8], eax

	invoke ExitProcess,0
main endp
end main